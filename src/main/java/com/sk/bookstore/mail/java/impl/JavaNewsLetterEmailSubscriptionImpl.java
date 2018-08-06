/**
 * 
 */
package com.sk.bookstore.mail.java.impl;

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
 **/
@Component
public class JavaNewsLetterEmailSubscriptionImpl implements NewsLetterEmailSubscription {

	@Autowired
	@Qualifier("javaEmailServer")
	private EmailServer javaEmailServer;
	
	@Autowired
	private TemplateEngine templateEngine;

	@Override
	public void sendNewsLetterEmailSubscription(final NewsLetter newsLetter) {
		final Context context = new Context();
		context.setVariable("imageResourceName", "logo");
		final String emailText = templateEngine.process(TemplateFileNameEnum.NEWSLETTER_SUBSCRIPTION_EMAIL_TEMPLATE.fileName(), context);
		javaEmailServer.sendEmail(newsLetter.getEmailAddress(), EmailEnum.NEWS_LETTER_SUBSCRIPTION.emailSubject(), emailText);
	}
}
