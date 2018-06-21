/**
 * 
 */
package com.sk.bookstore.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.bookstore.domain.UserPayment;
import com.sk.bookstore.exception.function.DataBaseThrowingFunction;
import com.sk.bookstore.repository.UserPaymentRepository;
import com.sk.bookstore.service.UserPaymentService;

/**
 * @author Surendra Kumar
 *
 */
@Service
public class UserPaymentServiceImpl implements UserPaymentService {
	@Autowired
	private UserPaymentRepository userPaymentRepository;

	public Optional<UserPayment> findById(final Long id) {
		DataBaseThrowingFunction<Long, Optional<UserPayment>> userDataBaseThrowingFunction = (findById) -> {
			return Optional.ofNullable(userPaymentRepository.findOne(findById));
		};
		return userDataBaseThrowingFunction.apply(id);
	}

	public void removeById(final Long id) {
		userPaymentRepository.delete(id);
	}

}
