/**
 * 
 */
package com.sk.bookstore.config;

import java.util.Locale;

import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.sk.bookstore.domain.Order;
import com.sk.bookstore.domain.User;

/**
 * @author Surendra Kumar
 *
 */
@Component
public class MailConstructorImpl {

	@Autowired
	private TemplateEngine templateEngine;

	public MimeMessagePreparator createNewUserRegistrationeEmail(final User user, final String password) {
		return this.setContextAndEmail(user, password, "userRegistrationEmailTemplate", "SK Group - Bookstore User Registration");
	}

	public MimeMessagePreparator createForgottenPasswordEmail(final User user, final String password) {
		return this.setContextAndEmail(user, password, "forgotenPasswordRequestEmailTemplate",
				"SK Group - Bookstore Renewed Password");
	}

	public MimeMessagePreparator constructOrderConfirmationEmail(final User user, final Order order,
			final Locale locale) {
		Context context = new Context();
		context.setVariable("order", order);
		context.setVariable("user", user);
		context.setVariable("cartItemList", order.getCartItemList());
		final String text = templateEngine.process("orderConfirmationEmailTemplate", context);
		return this.setEmail(user.getEmail(), "SK Group - Bookstore Order Confirmation " + order.getId(), text);
	}

	private MimeMessagePreparator setContextAndEmail(final User user, final String password,
			final String templateFileName, final String subject) {
		Context context = new Context();
		context.setVariable("username", user.getUsername());
		context.setVariable("password", password);
		final String text = templateEngine.process(templateFileName, context);
		return this.setEmail(user.getEmail(), subject, text);
	}
	
	private MimeMessagePreparator setEmail(final String senderEmail, final String subject, final String message) {
		return (mimeMessage) -> {
			MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
			email.setTo(senderEmail);
			email.setFrom(new InternetAddress("sk.groups.info@gmail.com"));
			email.setSubject(subject);
			email.setText(message, true);
		};
	}
}
