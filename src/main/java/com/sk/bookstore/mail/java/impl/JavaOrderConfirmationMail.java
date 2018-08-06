/**
 * 
 */
package com.sk.bookstore.mail.java.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sk.bookstore.domain.Order;
import com.sk.bookstore.domain.User;
import com.sk.bookstore.mail.EmailServer;
import com.sk.bookstore.mail.OrderConfirmationMail;
import com.sk.bookstore.resource.constant.EmailEnum;
import com.sk.bookstore.resource.constant.TemplateFileNameEnum;

/**
 * @author Surendra Kumar
 *
 */
@Component
public class JavaOrderConfirmationMail extends OrderConfirmationMail {

	@Autowired
	@Qualifier("javaEmailServer")
	private EmailServer javaEmailServer;

	public void sendOrderConfirmationEmail(final User user, final Order order) {
		final String emailText = this.setContext(user, order, TemplateFileNameEnum.ORDER_CONFIRMATION_EMAIL_TEMPLATE.fileName());
		javaEmailServer.sendEmail(user.getEmail(), EmailEnum.ORDER_SUBJECT.emailSubject() + order.getId(), emailText);
	}
}
