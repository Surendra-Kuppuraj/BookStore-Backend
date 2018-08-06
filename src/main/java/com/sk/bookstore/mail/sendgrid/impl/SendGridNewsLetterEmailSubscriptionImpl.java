/**
 * 
 */
package com.sk.bookstore.mail.sendgrid.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.sk.bookstore.domain.NewsLetter;
import com.sk.bookstore.mail.EmailServer;
import com.sk.bookstore.mail.NewsLetterEmailSubscription;
import com.sk.bookstore.resource.constant.EmailEnum;
import com.sk.bookstore.resource.constant.TemplateFileNameEnum;

/**
 * @author Surendra Kumar
 *
 */
@Component
public class SendGridNewsLetterEmailSubscriptionImpl implements NewsLetterEmailSubscription {

	@Autowired
	@Qualifier("sendGridEmailServer")
	private EmailServer sendGridEmailServer;

	@Autowired
	private TemplateEngine templateEngine;

	@Override
	public void sendNewsLetterEmailSubscription(NewsLetter newsLetter) {
		final Context context = new Context();
		context.setVariable("imageResourceName", "logo");
		final String emailText = templateEngine
				.process(TemplateFileNameEnum.NEWSLETTER_SUBSCRIPTION_EMAIL_TEMPLATE.fileName(), context);
		sendGridEmailServer.sendEmail(newsLetter.getEmailAddress(), EmailEnum.NEWS_LETTER_SUBSCRIPTION.emailSubject(),
				emailText);
	}

}
