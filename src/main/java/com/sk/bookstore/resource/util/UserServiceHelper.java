/**
 * 
 */
package com.sk.bookstore.resource.util;

import java.security.Principal;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sk.bookstore.domain.User;
import com.sk.bookstore.service.UserService;

/**
 * @author Surendra Kumar
 *
 */
@Component
public class UserServiceHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceHelper.class);

	@Autowired
	private UserService userService;

	public User getUser(final Principal principal) {
		final String userName = principal.getName();
		Optional<User> optionalUser = userService.findByUserName(userName);
		if (!optionalUser.isPresent()) {
			LOGGER.error("Given username " + userName + " is not found at the moment in our application");
			// TODO add controller adviser.
		}
		return optionalUser.get();
	}

}
