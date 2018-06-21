/**
 * 
 */
package com.sk.bookstore.exception;

/**
 * @author Surendra Kumar
 *
 */
public class DataBaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public DataBaseException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public DataBaseException(Throwable errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage.getMessage();
	}

	public DataBaseException(String message, Throwable cause) {
		super(message, cause);
		this.errorMessage = message;
	}

	public DataBaseException() {
		super();
	}
}
