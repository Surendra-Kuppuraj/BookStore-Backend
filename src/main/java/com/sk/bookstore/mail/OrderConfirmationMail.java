/**
 * 
 */
package com.sk.bookstore.mail;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.sk.bookstore.domain.Order;
import com.sk.bookstore.domain.User;
import com.sk.bookstore.mail.impl.JavaEmailServer;
import com.sk.bookstore.mail.impl.SendGridEmailServer;

/**
 * @author Surendra Kumar
 *
 */
public interface OrderConfirmationMail {
	
	@PostConstruct
	default TemplateEngine getTemplateEngine() {
		return new TemplateEngine();
	}
	
	public void sendOrderConfirmationEmail(final User user, final Order order, final Locale locale);
	
	default <T> void setContextAndEmail(final User user, final Order order, final Locale locale, T t) {
		Context context = new Context();
		context.setVariable("order", order);
		context.setVariable("user", user);
		context.setVariable("cartItemList", order.getCartItemList());
		final String emailText = getTemplateEngine().process("orderConfirmationEmailTemplate", context);
		if(t instanceof JavaEmailServer) {
			((JavaEmailServer)t).sendEmail(user.getEmail(), "SK Group - Bookstore Order Confirmation " + order.getId(), emailText);
		}
		if(t instanceof SendGridEmailServer) {
			((SendGridEmailServer)t).sendEmail(user.getEmail(), "SK Group - Bookstore Order Confirmation " + order.getId(), emailText);
		}
	}
}
