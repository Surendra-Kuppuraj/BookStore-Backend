/**
 * 
 */
package com.sk.bookstore.resource;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.bookstore.domain.UserShipping;
import com.sk.bookstore.resource.constant.MessageEnum;
import com.sk.bookstore.resource.util.ResponseMessage;
import com.sk.bookstore.resource.util.UserServiceHelper;
import com.sk.bookstore.service.UserService;
import com.sk.bookstore.service.UserShippingService;

/**
 * @author Surendra Kumar
 *
 */
@RestController
@RequestMapping("/shipping")
public class ShippingResource {
	@Autowired
	private UserService userService;

	@Autowired
	private UserServiceHelper userServiceHelper;

	@Autowired
	private UserShippingService userShippingService;

	@PostMapping
	public ResponseEntity<ResponseMessage> addUserShipping(@RequestBody final UserShipping userShipping,
			final Principal principal) {
		userService.updateUserShipping(userShipping, userServiceHelper.getUser(principal));
		return new ResponseEntity<>(new ResponseMessage(MessageEnum.CREATE_SUCCESS), HttpStatus.OK);
	}

	@GetMapping
	public List<UserShipping> getUserShippingList(final Principal principal) {
		return userServiceHelper.getUser(principal).getUserShippingList();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseMessage> removeUserShipping(@PathVariable final Long id, final Principal principal) {
		userShippingService.removeById(id);
		return new ResponseEntity<>(new ResponseMessage(MessageEnum.REMOVE_SUCCESS), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<ResponseMessage> setDefaultUserShipping(@RequestBody final Long id,
			final Principal principal) {
		userService.setUserDefaultShipping(id, userServiceHelper.getUser(principal));
		return new ResponseEntity<>(new ResponseMessage(MessageEnum.DEFAULT_SUCCESS), HttpStatus.OK);
	}

}
