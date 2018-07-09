/**
 * 
 */
package com.sk.bookstore.mail.impl;

import java.util.Locale;

import javax.mail.internet.InternetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.sk.bookstore.domain.Order;
import com.sk.bookstore.domain.User;
import com.sk.bookstore.exception.EmailConstructorException;
import com.sk.bookstore.mail.MailConstructor;

/**
 * @author Surendra Kumar
 *
 */
@Component
public class JavaMailConstructor implements MailConstructor {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private Environment environment;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JavaMailConstructor.class);

	@Override
	public void createNewUserRegistrationeEmail(final User user, final String password) {
		 this.setContextAndEmail(user, password, "userRegistrationEmailTemplate", "SK Group - Bookstore User Registration");
	}

	@Override
	public void createForgottenPasswordEmail(final User user, final String password) {
		 this.setContextAndEmail(user, password, "forgotenPasswordRequestEmailTemplate",
				"SK Group - Bookstore Renewed Password");
	}
	
	@Override
	public void constructOrderConfirmationEmail(final User user, final Order order,
			final Locale locale) {
		Context context = new Context();
		context.setVariable("order", order);
		context.setVariable("user", user);
		context.setVariable("cartItemList", order.getCartItemList());
		final String text = templateEngine.process("orderConfirmationEmailTemplate", context);
		this.setEmail(user.getEmail(), "SK Group - Bookstore Order Confirmation " + order.getId(), text);
	}

	private void setContextAndEmail(final User user, final String password,
			final String templateFileName, final String subject) {
		Context context = new Context();
		context.setVariable("username", user.getUsername());
		context.setVariable("password", password);
		final String text = templateEngine.process(templateFileName, context);
		this.setEmail(user.getEmail(), subject, text);
	}
	
	private void setEmail(final String senderEmail, final String subject, final String message) {
		try {
			mailSender.send((mimeMessage) -> {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setTo(senderEmail);
				email.setFrom(new InternetAddress(environment.getProperty("spring.mail.username")));
				email.setSubject(subject);
				email.setText(message, true);
			});
		}catch(MailException ex) {
			LOGGER.error("Spring Java Email Server cannot be connected at the moment due to the following reason " , ex);
			throw new EmailConstructorException(ex);
		}
	}
}
