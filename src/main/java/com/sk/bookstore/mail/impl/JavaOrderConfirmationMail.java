/**
 * 
 */
package com.sk.bookstore.mail.impl;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sk.bookstore.domain.Order;
import com.sk.bookstore.domain.User;
import com.sk.bookstore.mail.EmailServer;
import com.sk.bookstore.mail.OrderConfirmationMail;

/**
 * @author Surendra Kumar
 *
 */
@Component
public class JavaOrderConfirmationMail implements OrderConfirmationMail {

	@Autowired
	@Qualifier("javaEmailServer")
	private EmailServer javaEmailServer;

	@Override
	public void sendOrderConfirmationEmail(User user, Order order, Locale locale) {
		setContextAndEmail(user, order, locale, javaEmailServer);
	}
}