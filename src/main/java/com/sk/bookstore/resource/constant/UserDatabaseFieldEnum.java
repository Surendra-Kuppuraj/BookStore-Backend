/**
 * 
 */
package com.sk.bookstore.resource.constant;

/**
 * @author Surendra Kumar
 *
 */
public enum UserDatabaseFieldEnum {
	
	ID("id"), 
	FIRST_NAME("firstName"), 
	LAST_NAME("lastName"), 
	USER_NAME("userName"), 
	USER("user"),
	EMAIL("email"), 
	PASSWORD("password"),
	NEW_PASSWORD("newPassword"), 
	CHECK_PASSWORD("checkNewPassword"), 
	CURRENT_PASSWORD("currentPassword"), 
	ROLE_USER("ROLE_USER");

	private final String fieldName;

	private UserDatabaseFieldEnum(String fieldName) {
		this.fieldName = fieldName;
	}

	public String fieldName() {
		return fieldName;
	}

}
