/**
 * 
 */
package com.sk.bookstore.converter;

import java.beans.PropertyEditorSupport;

import com.sk.bookstore.resource.constant.ResourceURIType;

/**
 * @author Surendra Kumar
 *
 */
public class URITypeConverter  extends PropertyEditorSupport {
	 public void setAsText(final String text) throws IllegalArgumentException {
	        setValue(ResourceURIType.fromValue(text));
	    }
}
