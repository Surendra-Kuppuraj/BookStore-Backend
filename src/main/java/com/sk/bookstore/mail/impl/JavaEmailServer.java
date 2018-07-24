/**
 * 
 */
package com.sk.bookstore.mail.impl;

import javax.mail.internet.InternetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
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
	private static final Logger LOGGER = LoggerFactory.getLogger(JavaEmailServer.class);

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private Environment environment;

	@Override
	public void sendEmail(final String emailTo, final String subject, final String emailText) {
		try {
			mailSender.send((mimeMessage) -> {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				email.setTo(emailTo);
				email.setFrom(new InternetAddress(environment.getProperty("bookstore.support.email")));
				email.setSubject(subject);
				email.setText(emailText, true);
				email.addInline("logo", new ClassPathResource("static/image/sklogos/keys.png"), "image/png");
			});
			LOGGER.info("Email {} has been send successfully to the user ", emailTo);
		} catch (MailException ex) {
			LOGGER.error("Spring Java Email Server cannot be connected at the moment due to the following reason ", ex);
			throw new EmailConstructorException(ex);
		}
	}

}
