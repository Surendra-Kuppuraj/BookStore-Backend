/**
 * 
 */
package com.sk.bookstore.mail.java.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sk.bookstore.domain.User;
import com.sk.bookstore.mail.EmailServer;
import com.sk.bookstore.mail.UserAccountEmailConstructor;
import com.sk.bookstore.resource.constant.EmailEnum;
import com.sk.bookstore.resource.constant.TemplateFileNameEnum;

/**
 * @author Surendra Kumar
 *
 */
@Component
public class JavaUserAccountEmailConstructor extends UserAccountEmailConstructor {

	@Autowired
	@Qualifier("javaEmailServer")
	private EmailServer javaEmailServer;

	@Override
	public void sendNewUserRegistrationeEmail(final User user, final String password) {
		final String emailText = this.setContext(user, password, TemplateFileNameEnum.USER_REGISTRATION_EMIAL_TEMPLATE.fileName());
		javaEmailServer.sendEmail(user.getEmail(), EmailEnum.USER_REGISTRATION_SUBJECT.emailSubject(), emailText);
	}

	@Override
	public void sendForgottenPasswordEmail(final User user, final String password) {
		final String emailText = this.setContext(user, password, TemplateFileNameEnum.FORGOTTEN_PASSWORD_REQUEST_EMAIL_TEMPLATE.fileName());
		javaEmailServer.sendEmail(user.getEmail(), EmailEnum.FORGOTTEN_PASSWORD_SUBJECT.emailSubject(), emailText);
	}
}
