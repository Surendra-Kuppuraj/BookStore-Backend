/**
 * 
 */
package com.sk.bookstore.mail.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sk.bookstore.domain.User;
import com.sk.bookstore.mail.EmailServer;
import com.sk.bookstore.mail.UserAccountEmailConstructor;

/**
 * @author Surendra Kumar
 *
 */
@Component
public class SendGridUserAccountConstructor implements UserAccountEmailConstructor {
	
	@Autowired
	@Qualifier("sendGridEmailServer")
	private EmailServer sendGridEmailHelper;

	@Override
	public void sendNewUserRegistrationeEmail(User user, String password) {
		this.setContextAndEmail(user, password, "userRegistrationEmailTemplate",
				"SK International Group - Bookstore User Registration", sendGridEmailHelper);
	}

	@Override
	public void sendForgottenPasswordEmail(User user, String password) {
		this.setContextAndEmail(user, password, "forgotenPasswordRequestEmailTemplate",
				"SK International Group - Bookstore Renewed Password", sendGridEmailHelper);
	}
}
