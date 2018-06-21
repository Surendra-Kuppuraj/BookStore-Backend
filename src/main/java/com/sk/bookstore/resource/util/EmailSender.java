/**
 * 
 */
package com.sk.bookstore.resource.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.sk.bookstore.config.MailConstructor;
import com.sk.bookstore.domain.User;

/**
 * @author Surendra Kumar
 *
 */
@Component("emailSender")
public class EmailSender {

	@Autowired
	private MailConstructor mailConstructor;

	@Autowired
	private JavaMailSender mailSender;

	public void sendEmail(User user, String password) {
		SimpleMailMessage newEmail = mailConstructor.constructNewUserEmail(user, password);
		mailSender.send(newEmail);
	}

}
