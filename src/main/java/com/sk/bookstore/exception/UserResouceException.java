/**
 * 
 */
package com.sk.bookstore.exception;

/**
 * @author Surendra Kumar
 *
 */
public class UserResouceException extends RuntimeException {

	private static final long serialVersionUID = 1676882409L;
	private String errorMessage;

	public UserResouceException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	public String getErrorMessage() {
		return this.errorMessage;
	}

}
