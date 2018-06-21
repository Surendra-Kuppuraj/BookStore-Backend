/**
 * 
 */
package com.sk.bookstore.resource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.bookstore.config.MailConstructor;
import com.sk.bookstore.config.SecurityUtility;
import com.sk.bookstore.domain.User;
import com.sk.bookstore.domain.security.Role;
import com.sk.bookstore.domain.security.UserRole;
import com.sk.bookstore.resource.constant.MessageEnum;
import com.sk.bookstore.resource.util.ResponseMessage;
import com.sk.bookstore.service.UserService;

/**
 * @author Surendra Kumar
 *
 */
@RestController
@RequestMapping("/account")
public class UserAccountResource {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountResource.class);

	private static final String USER_NAME = "userName";
	private static final String EMAIL = "email";
	private static final String ROLE_USER = "ROLE_USER";

	@Autowired
	private UserService userService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private MailConstructor mailConstructor;

	@PostMapping("/new")
	public ResponseEntity<ResponseMessage> createUser(HttpServletRequest request,
			@Valid @RequestBody HashMap<String, String> mapper) throws Exception {
		String userName = mapper.get(USER_NAME);
		String userEmail = mapper.get(EMAIL);
		LOGGER.info("Started creating user account for user: " + userName + " with email-id: " + userEmail);
		
		Optional<User> userByName = userService.findByUserName(userName);
		if (userByName.isPresent()) {
			LOGGER.error("The user name already exists " + userName);
			return new ResponseEntity<>(new ResponseMessage(MessageEnum.USERNAME_EXISTS), HttpStatus.BAD_REQUEST);
		}
		Optional<User> userByEmail = userService.findByEmail(userEmail);
		if (userByEmail.isPresent()) {
			LOGGER.error("The user email already exists " + userEmail);
			return new ResponseEntity<>(new ResponseMessage(MessageEnum.EMAIL_EXISTS), HttpStatus.BAD_REQUEST);
		}
		
		String password = SecurityUtility.randomPassword();

		User user = new User();
		user.setUsername(userName);
		user.setEmail(userEmail);
		user.setPassword(generateEncryptedPassword(password));

		Role role = new Role();
		role.setRoleId(1);
		role.setName(ROLE_USER);

		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(user, role));
		User createdUser = userService.createUser(user, userRoles);
		if (Objects.isNull(createdUser)) {
			return new ResponseEntity<>(new ResponseMessage(MessageEnum.USER_CREATION_FAILED), HttpStatus.BAD_REQUEST);
		}
		mailSender.send(mailConstructor.createNewUserRegistrationeEmail(createdUser, password));
		return new ResponseEntity<>(new ResponseMessage(MessageEnum.USER_CREATION_SUCCESS), HttpStatus.OK);
	}
	
	@PutMapping("/forgottenPassword")
	public ResponseEntity<ResponseMessage> forgottenPassword(HttpServletRequest request,
			@Valid @RequestBody HashMap<String, String> mapper) throws Exception {
		String userEmail = mapper.get(EMAIL);
		LOGGER.info("Recovering the password for the user by Email " + userEmail);

		Optional<User> optionalUser = userService.findByEmail(userEmail);
		if (!optionalUser.isPresent()) {
			return new ResponseEntity<>(new ResponseMessage(MessageEnum.EMAIL_NOT_FOUND), HttpStatus.BAD_REQUEST);
		}
		
		User user = optionalUser.get();
		String password = SecurityUtility.randomPassword();
		user.setPassword(generateEncryptedPassword(password));
		
		Optional<User> savedUser = userService.userSave(user);
		if (!savedUser.isPresent()) {
			return new ResponseEntity<>(new ResponseMessage(MessageEnum.UPDATE_FAILED), HttpStatus.BAD_REQUEST);
		}
		mailSender.send(mailConstructor.createForgottenPasswordEmail(user, password));
		return new ResponseEntity<>(new ResponseMessage(MessageEnum.EMAIL_SENT), HttpStatus.OK);
	}
	
	private String generateEncryptedPassword(final String password) {
		return SecurityUtility.passwordEncoder().encode(password);
	}
}
