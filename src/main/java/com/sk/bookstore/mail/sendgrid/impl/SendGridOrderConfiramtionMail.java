/**
 * 
 */
package com.sk.bookstore.mail.sendgrid.impl;

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
public class SendGridOrderConfiramtionMail extends OrderConfirmationMail {
	
	@Autowired
	@Qualifier("sendGridEmailServer")
	private EmailServer sendGridEmailServer;

	@Override
	public void sendOrderConfirmationEmail(final User user, final Order order) {
		final String emailText = this.setContext(user, order, TemplateFileNameEnum.ORDER_CONFIRMATION_EMAIL_TEMPLATE.fileName());
		sendGridEmailServer.sendEmail(user.getEmail(), EmailEnum.ORDER_SUBJECT.emailSubject() + order.getId(), emailText);
	}
}