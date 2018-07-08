/**
 * 
 */
package com.sk.bookstore.config;

import java.io.IOException;
import java.util.Locale;

import org.springframework.stereotype.Component;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sk.bookstore.domain.Order;
import com.sk.bookstore.domain.User;

/**
 * @author Surendra Kumar
 *
 */
@Component
public class MailGunConstructor implements MailConstructor {
	
	@Override
	public void createNewUserRegistrationeEmail(User user, String password) {
		    Email from = new Email("surendra.kumar.kuppuraj@gmail.com");
		    String subject = "Sending with SendGrid is Fun";
		    Email to = new Email(user.getEmail());
		    Content content = new Content("text/plain", user.getUsername() + " -- "+password);
		    Mail mail = new Mail(from, subject, to, content);

		    SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
		    Request request = new Request();
		    try {
		      request.setMethod(Method.POST);
		      request.setEndpoint("mail/send");
		      request.setBody(mail.build());
		      Response response = sg.api(request);
		      System.out.println(response.getStatusCode());
		      System.out.println(response.getBody());
		      System.out.println(response.getHeaders());
		    } catch (IOException ex) {
		      
		    }
	}

	
	@Override
	public void createForgottenPasswordEmail(User user, String password) {

	}

	@Override
	public void constructOrderConfirmationEmail(User user, Order order, Locale locale) {

	}

}
