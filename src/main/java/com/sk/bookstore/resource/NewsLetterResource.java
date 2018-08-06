/**
 * 
 */
package com.sk.bookstore.resource;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.bookstore.domain.NewsLetter;
import com.sk.bookstore.mail.NewsLetterEmailSubscription;
import com.sk.bookstore.resource.constant.MessageEnum;
import com.sk.bookstore.resource.util.ResponseMessage;
import com.sk.bookstore.service.NewsLetterService;

/**
 * @author Surendra Kumar
 *
 */
@RestController
@RequestMapping("/newsletter")
public class NewsLetterResource {

	@Autowired
	private NewsLetterService newsLetterService;

	@Autowired
	private Environment environment;

	@Autowired
	@Qualifier(value = "javaNewsLetterEmailSubscriptionImpl")
	private NewsLetterEmailSubscription javaNewsLetterEmailSubscription;

	@Autowired
	@Qualifier(value = "sendGridNewsLetterEmailSubscriptionImpl")
	private NewsLetterEmailSubscription sendGridNewsLetterEmailSubscription;

	@PostMapping
	public ResponseEntity<ResponseMessage> subscribe(@RequestBody final String emailAddress) {
		System.out.println("I am in");
		NewsLetter newsLetter = new NewsLetter();
		newsLetter.setEmailAddress(emailAddress);
		newsLetter.setActive(true);
		newsLetter = newsLetterService.save(newsLetter);

		if (Objects.isNull(newsLetter)) {
			return new ResponseEntity<>(new ResponseMessage(MessageEnum.CREATE_FAILED), HttpStatus.OK);
		}

		final String applicationMode = environment.getProperty("spring.profiles.active");
		System.out.println("I am in");

		// For Development environment.
		if ("dev".equals(applicationMode)) {
			javaNewsLetterEmailSubscription.sendNewsLetterEmailSubscription(newsLetter);
		}

		// For Production environment.
		if ("production".equals(applicationMode)) {
			sendGridNewsLetterEmailSubscription.sendNewsLetterEmailSubscription(newsLetter);
		}
		return new ResponseEntity<>(new ResponseMessage(MessageEnum.NEWSLETTER_SUBSCRIBED), HttpStatus.OK);
	}
}
