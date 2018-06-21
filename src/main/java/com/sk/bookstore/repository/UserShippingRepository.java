/**
 * 
 */
package com.sk.bookstore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sk.bookstore.domain.User;
import com.sk.bookstore.domain.UserShipping;

/**
 * @author Surendra Kumar
 *
 */
public interface UserShippingRepository extends CrudRepository<UserShipping, Long>{
	List<UserShipping> findByUser(User user);
}
