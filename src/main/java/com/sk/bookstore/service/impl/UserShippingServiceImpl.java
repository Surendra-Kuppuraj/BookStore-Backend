package com.sk.bookstore.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sk.bookstore.domain.UserShipping;
import com.sk.bookstore.exception.function.DataBaseThrowingFunction;
import com.sk.bookstore.repository.UserShippingRepository;
import com.sk.bookstore.service.UserShippingService;

/**
 * @author Surendra Kumar
 *
 */
@Service
@Transactional
public class UserShippingServiceImpl implements UserShippingService {

	@Autowired
	private UserShippingRepository userShippingRepository;

	@Override
	public Optional<UserShipping> findById(Long id) {
		DataBaseThrowingFunction<Long, Optional<UserShipping>> userDataBaseThrowingFunction = (findById) -> {
			return Optional.ofNullable(userShippingRepository.findOne(findById));
		};
		return userDataBaseThrowingFunction.apply(id);

	}

	@Override
	public void removeById(Long id) {
		userShippingRepository.delete(id);
	}
}
