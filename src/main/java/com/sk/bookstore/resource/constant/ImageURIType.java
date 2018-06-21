/**
 * 
 */
package com.sk.bookstore.resource.constant;

import java.util.Arrays;

/**
 * @author Surendra Kumar
 *
 */
public enum ImageURIType {

	ADDIMAGE("addImage"), UPDATEIMAGE("updateImage");

	private final String uriPath;

	ImageURIType(final String uriPath) {
		this.uriPath = uriPath;
	}

	public String toString() {
		return this.uriPath;
	}

	public static ImageURIType fromValue(final String value) {
		for (ImageURIType path : values()) {
			if (path.uriPath.equalsIgnoreCase(value)) {
				return path;
			}
		}
		throw new IllegalArgumentException(
				"Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
	}

}
