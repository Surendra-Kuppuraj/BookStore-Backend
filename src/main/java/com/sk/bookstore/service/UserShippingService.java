/**
 * 
 */
package com.sk.bookstore.service;

import java.util.Optional;

import com.sk.bookstore.domain.UserShipping;

/**
 * @author Surendra Kumar
 *
 */
public interface UserShippingService {
	Optional<UserShipping> findById(Long id);

	void removeById(Long id);

}
