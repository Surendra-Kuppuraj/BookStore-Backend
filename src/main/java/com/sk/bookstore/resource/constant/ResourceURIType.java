/**
 * 
 */
package com.sk.bookstore.resource.constant;

import java.util.Arrays;

/**
 * @author Surendra Kumar
 *
 */
public enum ResourceURIType {
	ADD("add"), REMOVE("remove"), DEFAULT("setDefault"), USERLIST("getUserList"), NEW_USER_URI(
			"newUser"), FORGOTTEN_PASWORD_URI("recovery"),RECOVERY("recovery"),SING_UP("singup");

	private final String uri;

	private ResourceURIType(String uri) {
		this.uri = uri;
	}

	public String toString() {
		return this.uri;
	}

	public static ResourceURIType fromValue(final String value) {
		for (ResourceURIType path : values()) {
			if (path.uri.equalsIgnoreCase(value)) {
				return path;
			}
		}
		throw new IllegalArgumentException(
				"Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
	}

}
