/**
 * 
 */
package com.sk.bookstore.resource.constant;

/**
 * @author Surendra Kumar
 *
 */
public enum MessageEnum {
	REMOVE_SUCCESS("Removed Successfully!"), REMOVE_FAILED("Remove Failed!"), 
	UPLOAD_SUCCESS("Uploaded Successfully!"), UPLOAD_FAILED("Upload Failed!"),
	UPDATE_SUCCESS("Updated Successfully!"), UPDATE_FAILED("Update Failed!"),
	CREATE_SUCCESS("Created Successfuly!"), CREATE_FAILED("Creation Failed!"),
	ADD_SUCCESS("Added Successfuly!"), ADD_FAILED("Adding Failed!"),
	CHANGE_SUCCESS("Changed Successfully!"), CHANGE_FAILED("Change Failed!"),
	SESSION_ACTIVATE("Session Activated!"), LOGOUT_SUCCESS("Logout Successfully!"),
	DEFAULT_SUCCESS("Set Default Successfully!"),DEFAULT_FAILED("Setting Default Failed!"),
	EMAIL_NOT_FOUND("Email Not Found"), USERNAME_EXISTS("User Name Exists"),
	EMAIL_EXISTS("Email Exists"), EMAIL_SENT("Email Sent"), USERNAME_NOT_FOUND("User Name Not Found!"),
	INCORRECT_PASSWORD("Incorrect Current Password"), USER_CREATION_SUCCESS("User created Successfully!"),
	USER_CREATION_FAILED("User creation Failed!"), USER_NOT_FOUND("User not found!"),
	NOT_ENOUGH_STOCK("Not Enough Stock!"),BOOK_ADDED_SUCCESS("Book Added Successfully!"),
	BOOK_ADDED_FAILED("Book Adding to the cart failed!");;
	
	private final String message;

	private MessageEnum(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	@Override 
    public String toString(){ 
        return this.message; 
	}
}
