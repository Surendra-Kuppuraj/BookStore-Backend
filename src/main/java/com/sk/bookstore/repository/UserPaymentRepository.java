/**
 * 
 */
package com.sk.bookstore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sk.bookstore.domain.User;
import com.sk.bookstore.domain.UserPayment;



/**
 * @author Surendra Kumar
 *
 */
public interface UserPaymentRepository extends CrudRepository<UserPayment, Long> {
	List<UserPayment> findByUser(User user);
}
