/**
 * 
 */
package com.sk.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.sk.bookstore.domain.BookToCartItem;
import com.sk.bookstore.domain.CartItem;

/**
 * @author Surendra Kumar
 *
 */
public interface BookToCartItemRepository extends CrudRepository<BookToCartItem, Long>{
	void deleteByCartItem(CartItem cartItem);
}

