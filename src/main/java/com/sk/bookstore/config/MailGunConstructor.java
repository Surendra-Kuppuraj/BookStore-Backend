/**
 * 
 */
package com.sk.bookstore.config;

import java.util.Locale;

import org.springframework.stereotype.Component;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
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
		 HttpResponse<JsonNode> request;
		try {
			request = Unirest.post("https://api.mailgun.net/v3/" +System.getenv().get("MAILGUN_DOMAIN") + "/messages")
			         .basicAuth("api", System.getenv().get("MAILGUN_API_KEY"))
			     .queryString("from", System.getenv().get("MAILGUN_SMTP_LOGIN"))
			     .queryString("to", "artemis@example.com")
			     .queryString("subject", "hello")
			     .queryString("text", "testing")
			     .asJson();
			request.getBody();
		} catch (UnirestException e) {
			e.printStackTrace();
		}	
	}

	
	@Override
	public void createForgottenPasswordEmail(User user, String password) {

	}

	@Override
	public void constructOrderConfirmationEmail(User user, Order order, Locale locale) {

	}

}
