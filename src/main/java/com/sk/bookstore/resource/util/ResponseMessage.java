/**
 * 
 */
package com.sk.bookstore.resource.util;

import com.sk.bookstore.resource.constant.MessageEnum;

/**
 * @author Surendra Kumar
 *
 */
public class ResponseMessage {

	private String code;
	private String message;

	public ResponseMessage(MessageEnum messageEnum) {
		this.code = messageEnum.name();
		this.message = messageEnum.getMessage();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
