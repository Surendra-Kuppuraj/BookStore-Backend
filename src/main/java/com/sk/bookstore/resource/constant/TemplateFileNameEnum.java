/**
 * 
 */
package com.sk.bookstore.resource.constant;

/**
 * @author Surendra Kumar
 *
 */
public enum TemplateFileNameEnum {
	
	FORGOTTEN_PASSWORD_REQUEST_EMAIL_TEMPLATE("forgottenPasswordRequestEmailTemplate"), 
	USER_REGISTRATION_EMIAL_TEMPLATE("userRegistrationEmailTemplate"),
	ORDER_CONFIRMATION_EMAIL_TEMPLATE("orderConfirmationEmailTemplate");
	
	private final String fileName;

	private TemplateFileNameEnum(String fileName) {
		this.fileName = fileName;
	}

	public String fileName() {
		return fileName;
	}
}
