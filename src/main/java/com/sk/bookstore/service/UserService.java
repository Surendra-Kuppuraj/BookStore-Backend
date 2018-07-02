/**
 * 
 */
package com.sk.bookstore.service;

import java.util.Optional;
import java.util.Set;

import com.sk.bookstore.domain.User;
import com.sk.bookstore.domain.UserBilling;
import com.sk.bookstore.domain.UserPayment;
import com.sk.bookstore.domain.UserShipping;
import com.sk.bookstore.domain.security.UserRole;

/**
 * @author Surendra Kumar
 *
 */
public interface UserService {

	User createUser(User user, Set<UserRole> userRoles);

	Optional<User> findByUserName(String userName);

	Optional<User> findByEmail(String email);

	Optional<User> userSave(User user);

	Optional<User> findById(Long id);

	void updateUserPaymentInfo(UserBilling userBilling, UserPayment userPayment, User user);

	void updateUserBilling(User user, UserPayment userPayment);

	void setUserDefaultPayment(Long userPaymentId, User user);
	
	void updateUserShipping(UserShipping userShipping, User user);

	void setUserDefaultShipping(Long userShippingId, User user);
}
