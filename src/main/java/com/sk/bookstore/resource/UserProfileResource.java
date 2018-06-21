/**
 * 
 */
package com.sk.bookstore.resource;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.bookstore.config.SecurityUtility;
import com.sk.bookstore.domain.User;
import com.sk.bookstore.resource.constant.MessageEnum;
import com.sk.bookstore.resource.util.ResponseHttpStatusMessage;
import com.sk.bookstore.resource.util.ResponseMessage;
import com.sk.bookstore.resource.util.UserServiceHelper;
import com.sk.bookstore.service.UserService;

/**
 * @author Surendra Kumar
 *
 */
@RestController
@RequestMapping("/profile")
public class UserProfileResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserProfileResource.class);
	private static final String USER_NAME = "userName";
	private static final String EMAIL = "email";

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserServiceHelper userServiceHelper;

	@PutMapping()
	public ResponseEntity<?> profileInfo(@Valid @RequestBody HashMap<String, Object> mapper) throws Exception {
		final int id = (Integer) mapper.get("id");
		final String email = (String) mapper.get(EMAIL);
		final String username = (String) mapper.get(USER_NAME);
		final String firstName = (String) mapper.get("firstName");
		final String lastName = (String) mapper.get("lastName");
		final String newPassword = (String) mapper.get("newPassword");
		final String checkNewPassword = (String) mapper.get("checkNewPassword");
		final String currentPassword = (String) mapper.get("currentPassword");

		Optional<User> currentUserById = userService.findById(Long.valueOf(id));
		if (!currentUserById.isPresent()) {
			return new ResponseEntity<>(new ResponseMessage(MessageEnum.UPDATE_FAILED), HttpStatus.BAD_REQUEST);
		}

		final List<String> errorList = new ArrayList<String>();
		BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
		final User currentUser = currentUserById.get();
		final Long currentUserId = currentUser.getId();
		final String dbPassword = currentUser.getPassword();

		Optional<User> currentUserByEmail = userService.findByEmail(email);
		if (currentUserByEmail.isPresent() && currentUserByEmail.get().getId() != currentUserId) {
			errorList.add("EmailAlredyExists");
		}
		Optional<User> currentUserByUserName = userService.findByUserName(username);
		if (!currentUserByUserName.isPresent() && currentUserByUserName.get().getId() != currentUserId) {
			errorList.add("UserNameAlreadyExists");
		}
		if (isNullOrEmpty(newPassword) || isNullOrEmpty(checkNewPassword) || !newPassword.equals(checkNewPassword)) {
			errorList.add("NewPasswordMismatch");
		}
		if (isNullOrEmpty(currentPassword)) {
			errorList.add("MissingCurrentPassword");
		}
		if (!passwordEncoder.matches(currentPassword, dbPassword)) {
			errorList.add("MissMatchedPassword");
		}
		if (!errorList.isEmpty()) {
			LOGGER.error("Various error has been found while updating user name "+ username  +" with profile: " + errorList.toString());
			return new ResponseEntity<>(new ResponseHttpStatusMessage(errorList), HttpStatus.BAD_REQUEST);
		}

		currentUser.setPassword(passwordEncoder.encode(newPassword));
		currentUser.setFirstName(firstName);
		currentUser.setLastName(lastName);
		currentUser.setUsername(username);
		currentUser.setEmail(email);
		Optional<User> updatedUser = userService.userSave(currentUser);
		if (!updatedUser.isPresent()) {
			return new ResponseEntity<>(new ResponseMessage(MessageEnum.UPDATE_FAILED), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new ResponseMessage(MessageEnum.UPDATE_SUCCESS), HttpStatus.OK);
	}

	@GetMapping
	public User getCurrentUser(Principal principal) {
		return userServiceHelper.getUser(principal);
	}

	private boolean isNullOrEmpty(final String input) {
		return (input == null || input.trim().isEmpty());
	}
}
