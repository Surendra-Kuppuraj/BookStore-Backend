/**
 * 
 */
package com.sk.bookstore.config;

import java.util.Locale;

import com.sk.bookstore.domain.Order;
import com.sk.bookstore.domain.User;

/**
 * @author Surendra Kumar
 *
 */
public interface MailConstructor {
	public void createNewUserRegistrationeEmail(final User user, final String password);
	public void createForgottenPasswordEmail(final User user, final String password);
	public void constructOrderConfirmationEmail(final User user, final Order order,
			final Locale locale);
}
