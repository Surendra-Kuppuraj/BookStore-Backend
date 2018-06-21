/**
 * 
 */
package com.sk.bookstore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sk.bookstore.domain.Order;
import com.sk.bookstore.domain.User;

/**
 * @author Surendra Kumar
 *
 */
public interface OrderRepository extends CrudRepository<Order, Long> {
	List<Order> findByUser(User user);
}
