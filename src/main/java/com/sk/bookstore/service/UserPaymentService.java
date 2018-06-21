/**
 * 
 */
package com.sk.bookstore.service;

import java.util.Optional;

import com.sk.bookstore.domain.UserPayment;

/**
 * @author Surendra Kumar
 *
 */
public interface UserPaymentService {
	Optional<UserPayment> findById(Long id);
	
	void removeById(Long id);
}

