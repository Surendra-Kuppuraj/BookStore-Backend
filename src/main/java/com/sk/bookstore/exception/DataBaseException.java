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
	private final String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public DataBaseException(final String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public DataBaseException(Throwable errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage.getMessage();
	}

}
