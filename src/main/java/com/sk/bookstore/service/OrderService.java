/**
 * 
 */
package com.sk.bookstore.service;

import com.sk.bookstore.domain.BillingAddress;
import com.sk.bookstore.domain.Order;
import com.sk.bookstore.domain.Payment;
import com.sk.bookstore.domain.ShippingAddress;
import com.sk.bookstore.domain.ShoppingCart;
import com.sk.bookstore.domain.User;

/**
 * @author Surendra Kumar
 *
 */
public interface OrderService {
	Order createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress, BillingAddress billingAddress,
			Payment payment, String shippingMethod, User user);

	Order findOne(Long id);
}
