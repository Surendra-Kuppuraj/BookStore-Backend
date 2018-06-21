/**
 * 
 */
package com.sk.bookstore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sk.bookstore.domain.CartItem;
import com.sk.bookstore.domain.ShoppingCart;

/**
 * @author Surendra Kumar
 *
 */
public interface CartItemRepository extends CrudRepository<CartItem, Long> {
	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
}
