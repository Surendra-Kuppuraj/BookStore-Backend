/**
 * 
 */
package com.sk.bookstore.mail.sendgrid.impl;

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
public class SendGridUserAccountEmailConstructor extends UserAccountEmailConstructor {

	@Autowired
	@Qualifier("sendGridEmailServer")
	private EmailServer sendGridEmailServer;

	@Override
	public void sendNewUserRegistrationeEmail(User user, String password) {
		final String emailText = this.setContext(user, password,
				TemplateFileNameEnum.USER_REGISTRATION_EMIAL_TEMPLATE.fileName());
		sendGridEmailServer.sendEmail(user.getEmail(), EmailEnum.USER_REGISTRATION_SUBJECT.emailSubject(), emailText);
	}

	@Override
	public void sendForgottenPasswordEmail(User user, String password) {
		final String emailText = this.setContext(user, password,
				TemplateFileNameEnum.FORGOTTEN_PASSWORD_REQUEST_EMAIL_TEMPLATE.fileName());
		sendGridEmailServer.sendEmail(user.getEmail(), EmailEnum.FORGOTTEN_PASSWORD_SUBJECT.emailSubject(), emailText);
	}
}
