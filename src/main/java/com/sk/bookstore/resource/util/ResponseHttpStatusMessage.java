/**
 * 
 */
package com.sk.bookstore.resource.util;

import java.util.List;

import com.sk.bookstore.resource.constant.MessageEnum;

/**
 * @author Surendra Kumar
 *
 */
public class ResponseHttpStatusMessage {

	private String message;
	private String description;
	private MessageEnum messageEnum;
	private List<String> failedMessageList;

	public ResponseHttpStatusMessage(String message, String description) {
		this.message = message;
		this.description = description;
	}

	public ResponseHttpStatusMessage(List<String> failedMessageList) {
		this.failedMessageList = failedMessageList;
	}

	public ResponseHttpStatusMessage(MessageEnum messageEnum, String description) {
		this.messageEnum = messageEnum;
		this.description = description;
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

	public MessageEnum getMessageEnum() {
		return messageEnum;
	}

	public void setMessageEnum(MessageEnum messageEnum) {
		this.messageEnum = messageEnum;
	}
}
