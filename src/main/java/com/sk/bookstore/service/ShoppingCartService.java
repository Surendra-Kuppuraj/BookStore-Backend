/**
 * 
 */
package com.sk.bookstore.service;

import com.sk.bookstore.domain.ShoppingCart;

/**
 * @author Surendra Kumar
 *
 */
public interface ShoppingCartService {
	ShoppingCart updateShoppingCart(ShoppingCart shoppingCart);

	void clearShoppingCart(ShoppingCart shoppingCart);
}
