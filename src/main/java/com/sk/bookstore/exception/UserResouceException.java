/**
 * 
 */
package com.sk.bookstore.exception;

import com.sk.bookstore.resource.constant.MessageEnum;

/**
 * @author Surendra Kumar
 *
 */
public class UserResouceException extends Exception {

	private static final long serialVersionUID = 1676882409L;
	private String errorMessage;

	public UserResouceException(MessageEnum usernameExists) {
		super(usernameExists.toString());
		this.errorMessage = usernameExists.toString();
	}

	public UserResouceException() {
		super();
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
