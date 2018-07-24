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
import com.sk.bookstore.resource.constant.UserDatabaseFieldEnum;
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

	@Autowired
	private UserService userService;

	@Autowired
	private UserServiceHelper userServiceHelper;

	@PutMapping()
	public ResponseEntity<?> profileInfo(@Valid @RequestBody HashMap<String, Object> mapper) throws Exception {
		final int id = (Integer) mapper.get(UserDatabaseFieldEnum.ID.fieldName());
		final String email = (String) mapper.get(UserDatabaseFieldEnum.EMAIL.fieldName());
		final String username = (String) mapper.get(UserDatabaseFieldEnum.USER_NAME.fieldName());
		final String firstName = (String) mapper.get(UserDatabaseFieldEnum.FIRST_NAME.fieldName());
		final String lastName = (String) mapper.get(UserDatabaseFieldEnum.LAST_NAME.fieldName());
		final String newPassword = (String) mapper.get(UserDatabaseFieldEnum.NEW_PASSWORD.fieldName());
		final String checkNewPassword = (String) mapper.get(UserDatabaseFieldEnum.CHECK_PASSWORD.fieldName());
		final String currentPassword = (String) mapper.get(UserDatabaseFieldEnum.CURRENT_PASSWORD.fieldName());
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
			errorList.add("Email Alredy Exists");
		}
		Optional<User> currentUserByUserName = userService.findByUserName(username);
		if (!currentUserByUserName.isPresent() && currentUserByUserName.get().getId() != currentUserId) {
			errorList.add("User Name Already Exists");
		}
		if (isNullOrEmpty(newPassword) || isNullOrEmpty(checkNewPassword) || !newPassword.equals(checkNewPassword)) {
			errorList.add("New Password Mismatch");
		}

		if (isNullOrEmpty(currentPassword) || !passwordEncoder.matches(currentPassword, dbPassword)) {
			errorList.add("Wrong Current Password");
		}
		if (!errorList.isEmpty()) {
			final String errorDescription = "Various error has been found while updating user name " + username
					+ " with profile ";
			LOGGER.error(errorDescription + errorList.toString());
			return new ResponseEntity<>(new ResponseHttpStatusMessage("Invalid inputs", errorDescription, errorList),
					HttpStatus.BAD_REQUEST);
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
