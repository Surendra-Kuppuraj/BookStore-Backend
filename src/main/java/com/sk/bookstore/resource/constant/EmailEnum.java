/**
 * 
 */
package com.sk.bookstore.resource.constant;

/**
 * @author Surendra Kumar
 *
 */
public enum EmailEnum {
	
	ORDER_SUBJECT("SK International Group - Bookstore Order Confirmation"),
    FORGOTTEN_PASSWORD_SUBJECT("SK International Group - Bookstore Renewed Password"),
	USER_REGISTRATION_SUBJECT("SK International Group - Bookstore User Registration");
	
	private final String emailSubject;
	
	private EmailEnum(String emailSubject) {
		this.emailSubject =  emailSubject;
	}
	
	public String emailSubject() {
		return this.emailSubject;
	}
}
