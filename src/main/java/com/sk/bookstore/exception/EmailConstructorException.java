/**
 * 
 */
package com.sk.bookstore.exception;

/**
 * @author Surendra Kumar
 *
 */
public class EmailConstructorException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public EmailConstructorException(final String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
	public EmailConstructorException(Throwable errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage.getMessage();
	}
}
