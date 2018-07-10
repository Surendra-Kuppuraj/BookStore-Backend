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
public class JavaUserAccountEmailConstructor implements UserAccountEmailConstructor {
	
	@Autowired
	@Qualifier("javaEmailServer")
	private EmailServer javaEmailServer;
	
	@Override
	public void sendNewUserRegistrationeEmail(final User user, final String password) {
		 this.setContextAndEmail(user, password, "userRegistrationEmailTemplate", "SK Group - Bookstore User Registration", javaEmailServer);
	}

	@Override
	public void sendForgottenPasswordEmail(final User user, final String password) {
		 this.setContextAndEmail(user, password, "forgotenPasswordRequestEmailTemplate",
				"SK Group - Bookstore Renewed Password", javaEmailServer);
	}
}
