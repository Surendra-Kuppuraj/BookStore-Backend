/**
 * 
 */
package com.sk.bookstore.converter;

import java.beans.PropertyEditorSupport;

import com.sk.bookstore.resource.constant.ImageURIType;

/**
 * @author Surendra Kumar
 *
 */
public class ImageURITypeConverter  extends PropertyEditorSupport {
	 public void setAsText(final String text) throws IllegalArgumentException {
	        setValue(ImageURIType.fromValue(text));
	    }
}
