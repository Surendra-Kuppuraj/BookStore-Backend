/**
 * 
 */
package com.sk.bookstore.resource.constant;

/**
 * @author Surendra Kumar
 *
 */
public enum CheckoutDatabaseFieldEnum {
	BOOK_ID("bookId"),
	QTY("qty"),
	ORDER("order"),
	CART_ITEM_ID("cartItemId"),
	CART_ITEM_LIST("cartItemList"),
	SHIPPING_ADDRESS("shippingAddress"),
	BILLING_ADDRESS("billingAddress"),
	PAYMENT("payment"),
	SHIPPING_METHOD("shippingMethod");
	
	private final String fieldName;

	private CheckoutDatabaseFieldEnum(String fieldName) {
		this.fieldName = fieldName;
	}

	public String fieldName() {
		return fieldName;
	}

}
