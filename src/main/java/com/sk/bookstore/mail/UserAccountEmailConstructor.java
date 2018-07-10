/**
 * 
 */
package com.sk.bookstore.mail;

import javax.annotation.PostConstruct;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.sk.bookstore.domain.User;
import com.sk.bookstore.mail.impl.JavaEmailServer;
import com.sk.bookstore.mail.impl.SendGridEmailServer;

/**
 * @author Surendra Kumar
 *
 */
public interface UserAccountEmailConstructor {

	@PostConstruct
	default TemplateEngine getTemplateEngine() {
		return new TemplateEngine();
	}
	
	default <T> void setContextAndEmail(final User user, final String password,
			final String templateFileName, final String subject, T t) {
		Context context = new Context();
		context.setVariable("username", user.getUsername());
		context.setVariable("password", password);
		final String emailText = getTemplateEngine().process(templateFileName, context);
		if(t instanceof JavaEmailServer) {
			((JavaEmailServer)t).sendEmail(user.getEmail(), subject, emailText);
		}
		if(t instanceof SendGridEmailServer) {
			((SendGridEmailServer)t).sendEmail(user.getEmail(), subject, emailText);
		}
	}
	
	public void sendNewUserRegistrationeEmail(final User user, final String password);

	public void sendForgottenPasswordEmail(final User user, final String password);
}
