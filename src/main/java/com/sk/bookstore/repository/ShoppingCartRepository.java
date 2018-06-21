/**
 * 
 */
package com.sk.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.sk.bookstore.domain.ShoppingCart;

/**
 * @author Surendra Kumar
 *
 */
public interface ShoppingCartRepository  extends CrudRepository<ShoppingCart, Long>{

}
