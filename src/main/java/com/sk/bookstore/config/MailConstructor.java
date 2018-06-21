/**
 * 
 */
package com.sk.bookstore.config;

import java.util.Locale;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


import com.sk.bookstore.domain.Order;
import com.sk.bookstore.domain.User;

/**
 * @author Surendra Kumar
 *
 */
@Component
public class MailConstructor {

	@Autowired
	private Environment env;
	
	@Autowired
	private TemplateEngine templateEngine;


	// TODO Implement the template for Email using freemaker.
	public SimpleMailMessage constructNewUserEmail(User user, String password) {
		String message = "Your User name is " + user.getUsername() + " \n Your Password is " + password;
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(user.getEmail());
		email.setSubject("SK Groups Book Shop User Registration");
		email.setText(message);
		email.setFrom(env.getProperty("support.email"));
		return email;
	}

	public MimeMessagePreparator constructOrderConfirmationEmail(final User user, final Order order, final Locale locale) {
		Context context = new Context();
		context.setVariable("order", order);
		context.setVariable("user", user);
		context.setVariable("cartItemList", order.getCartItemList());
		String text = templateEngine.process("orderConfirmationEmailTemplate", context);

		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setTo(user.getEmail());
				email.setSubject("Order Confirmation - " + order.getId());
				email.setText(text, true);
				email.setFrom(new InternetAddress("sk.groups.info@gmail.com"));
			}
		};

		return messagePreparator;
	}

}
