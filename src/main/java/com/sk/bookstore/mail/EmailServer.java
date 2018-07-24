/**
 * 
 */
package com.sk.bookstore.mail;

/**
 * @author Surendra Kumar
 *
 */
public interface EmailServer {
	public void sendEmail(final String emailTo, final String subject, final String emailText);	 
}
