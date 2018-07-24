/**
 * 
 */
package com.sk.bookstore.resource.util;

import java.util.List;

/**
 * @author Surendra Kumar
 *
 */
public class ResponseHttpStatusMessage {

	private String message;
	private String description;
	private List<String> failedMessageList;

	public ResponseHttpStatusMessage(String message, String description, List<String> failedMessageList) {
		this.message = message;
		this.description = description;
		this.failedMessageList = failedMessageList;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getFailedMessageList() {
		return failedMessageList;
	}

	public void setFailedMessageList(List<String> failedMessageList) {
		this.failedMessageList = failedMessageList;
	}
}
