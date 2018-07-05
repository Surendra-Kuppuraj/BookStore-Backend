/**
 * 
 */
package com.sk.bookstore.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sk.bookstore.domain.User;

/**
 * @author Surendra Kumar
 *
 */
@Component
public class GridMailConstructor {

	@Autowired
	private TemplateEngine templateEngine;
	
	public void sendEmail(final User user, final String password) {
		    Email from = new Email("sk.groups.info@gmail.com");
		    String subject = "Sending with SendGrid is Fun" + user.getUsername()+ " " +password;
		    Email to = new Email("surendra.kumar.kuppuraj@gmail.com");
		    
		    
		    Context context = new Context();
			context.setVariable("username", user.getUsername());
			context.setVariable("password", password);
			final String text = templateEngine.process("userRegistrationEmailTemplate", context);
		    Content content = new Content("text/plain", text);
		    Mail mail = new Mail(from, subject, to, content);

		    SendGrid sg = new SendGrid("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");
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

	
}
