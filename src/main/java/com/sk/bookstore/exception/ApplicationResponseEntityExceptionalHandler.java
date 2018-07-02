/**
 * 
 */
package com.sk.bookstore.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Surendra Kumar
 *
 */
@ControllerAdvice
public class ApplicationResponseEntityExceptionalHandler extends ResponseEntityExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationResponseEntityExceptionalHandler.class);

	@ExceptionHandler(DataBaseException.class)
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		final String bodyOfResponse = "Our Backend application server has been down for a moment. Our Development team is on the way.";
		LOGGER.error("Database problem has been occured: " + ex.getMessage());
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

	@ExceptionHandler(value = { IllegalArgumentException.class, NumberFormatException.class })
	protected ResponseEntity<Object> handlePreCondition(RuntimeException ex, WebRequest request) {
		final String bodyOfResponse = "Invalid Argument has been given in the request parameter";
		LOGGER.error(bodyOfResponse + ": " + ex.getMessage());
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.PRECONDITION_FAILED, request);
	}

	@ExceptionHandler(UserResouceException.class)
	protected ResponseEntity<Object> exceptionHandler(UserResouceException ex, WebRequest request) {
		final String bodyOfResponse = "Invalid User name has given";
		LOGGER.error(bodyOfResponse + ": " + ex.getMessage());
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
	}
}
