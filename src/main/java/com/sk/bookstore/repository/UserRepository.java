/**
 * 
 */
package com.sk.bookstore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sk.bookstore.domain.User;

/**
 * @author Surendra Kumar
 *
 */
public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String userName);

	User findByEmail(String email);

	List<User> findAll();

	boolean existsById(Long arg0);
}
