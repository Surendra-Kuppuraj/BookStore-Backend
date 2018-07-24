/**
 * 
 */
package com.sk.bookstore.mail.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.sendgrid.Attachments;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sk.bookstore.exception.EmailConstructorException;
import com.sk.bookstore.mail.EmailServer;

/**
 * @author Surendra Kumar
 *
 */
@Component
public class SendGridEmailServer implements EmailServer {
	private static final Logger LOGGER = LoggerFactory.getLogger(SendGridEmailServer.class);

	@Autowired
	private Environment environment;

	@Override
	public void sendEmail(final String emailTo, final String subject, final String emailText) {
		// Setting up email content.
		final Content content = new Content("text/html", emailText);
		final Mail mail = new Mail(setEmailObject(environment.getProperty("bookstore.support.email")), subject,
				setEmailObject(emailTo), content);
		//Attaching logo image.
		 Attachments attachment = new Attachments();
		 attachment.setContent("logo");
		 attachment.setType("image/png");
		 attachment.setFilename("keys.png");
		 attachment.setDisposition("inline");
		 attachment.setContentId("imageResourceName");
		 mail.addAttachments(attachment);


		// Sending email using Email Server.
		SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			LOGGER.info("Send Grid Email Response Status Code", response.getStatusCode());
			LOGGER.info("Send Grid Email Body", response.getBody());
			LOGGER.info("Send Grid Email Header", response.getHeaders());
			LOGGER.info("Email {} has been send  successfully to the user ", emailTo);
		} catch (IOException ex) {
			LOGGER.error("Send Grid Email Server cannot be connected at the moment due to the following reason ", ex);
			throw new EmailConstructorException(ex);
		}
	}

	private Email setEmailObject(final String emailAddress) {
		return new Email(emailAddress);
	}
}
