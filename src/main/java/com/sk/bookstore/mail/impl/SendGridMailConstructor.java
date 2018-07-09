/**
 * 
 */
package com.sk.bookstore.mail.impl;

import java.io.IOException;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sk.bookstore.domain.Order;
import com.sk.bookstore.domain.User;
import com.sk.bookstore.exception.EmailConstructorException;
import com.sk.bookstore.mail.MailConstructor;

/**
 * @author Surendra Kumar
 *
 */
@Component
public class SendGridMailConstructor implements MailConstructor {

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private Environment environment;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SendGridMailConstructor.class);

	@Override
	public void createNewUserRegistrationeEmail(User user, String password) {
		this.setContextAndEmail(user, password, "userRegistrationEmailTemplate",
				"SK International Group - Bookstore User Registration");
	}

	@Override
	public void createForgottenPasswordEmail(User user, String password) {
		this.setContextAndEmail(user, password, "forgotenPasswordRequestEmailTemplate",
				"SK International Group - Bookstore Renewed Password");
	}

	@Override
	public void constructOrderConfirmationEmail(User user, Order order, Locale locale) {
		Context context = new Context();
		context.setVariable("order", order);
		context.setVariable("user", user);
		context.setVariable("cartItemList", order.getCartItemList());
		final String emailText = templateEngine.process("orderConfirmationEmailTemplate", context);
		final Content content = new Content("text/html", emailText);
		this.sendMail(new Mail(setEmailObject(environment.getProperty("spring.mail.username")), "SK International Group - Bookstore Order Confirmation ",
				setEmailObject(user.getEmail()), content));
	}

	private void setContextAndEmail(final User user, final String password, final String templateFileName,
			final String subject) {
		Context context = new Context();
		context.setVariable("username", user.getUsername());
		context.setVariable("password", password);
		final String emailText = templateEngine.process(templateFileName, context);
		final Content content = new Content("text/html", emailText);
		this.sendMail(new Mail(setEmailObject(environment.getProperty("spring.mail.username")), subject,
				setEmailObject(user.getEmail()), content));
	}

	private void sendMail(final Mail mail) {
		SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			LOGGER.info("Send Grid Email Response Status Code",response.getStatusCode());
			LOGGER.info("Send Grid Email Body",response.getBody());
			LOGGER.info("Send Grid Email Header",response.getHeaders());
		} catch (IOException ex) {
			LOGGER.error("Send Grid Email Server cannot be connected at the moment due to the following reason " , ex);
			throw new EmailConstructorException(ex);
		}	
	}

	private Email setEmailObject(final String emailAddress) {
		return new Email(emailAddress);
	}
}
