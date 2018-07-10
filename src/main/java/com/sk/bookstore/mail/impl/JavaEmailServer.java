/**
 * 
 */
package com.sk.bookstore.mail.impl;

import javax.mail.internet.InternetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.sk.bookstore.exception.EmailConstructorException;
import com.sk.bookstore.mail.EmailServer;

/**
 * @author Surendra Kumar
 *
 */
@Component
public class JavaEmailServer implements EmailServer {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private Environment environment;

	private static final Logger LOGGER = LoggerFactory.getLogger(JavaEmailServer.class);

	@Override
	public void sendEmail(final String emailTo, final String subject, final String emailText) {
		try {
			mailSender.send((mimeMessage) -> {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setTo(emailTo);
				email.setFrom(new InternetAddress(environment.getProperty("spring.mail.username")));
				email.setSubject(subject);
				email.setText(emailText, true);
			});
		} catch (MailException ex) {
			LOGGER.error("Spring Java Email Server cannot be connected at the moment due to the following reason ", ex);
			throw new EmailConstructorException(ex);
		}
	}

}
