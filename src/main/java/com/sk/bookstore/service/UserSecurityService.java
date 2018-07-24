/**
 * 
 */
package com.sk.bookstore.service;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sk.bookstore.domain.User;
import com.sk.bookstore.repository.UserRepository;

@Service
public class UserSecurityService implements UserDetailsService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserSecurityService.class);
	
	@Autowired 
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

		if(Objects.isNull(user)) {
			LOGGER.warn("Username {} not found", username);
			throw new UsernameNotFoundException("Username "+username+" not found");
		}
		LOGGER.info("User {} has been authenticated in our SK International Group " + username);
		return user;
	}
}