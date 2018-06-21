package com.sk.bookstore.resource;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.sk.bookstore.domain.UserPayment;
import com.sk.bookstore.resource.constant.MessageEnum;
import com.sk.bookstore.resource.util.ResponseMessage;
import com.sk.bookstore.resource.util.UserServiceHelper;
import com.sk.bookstore.service.UserPaymentService;
import com.sk.bookstore.service.UserService;

/**
 * @author Surendra Kumar
 *
 */
@RestController
@RequestMapping("/payments")
public class PaymentResource {

	@Autowired
	private UserService userService;

	@Autowired
	private UserServiceHelper userServiceHelper;

	@Autowired
	private UserPaymentService userPaymentService;

	@PostMapping()
	public ResponseEntity<ResponseMessage> addNewCreditCard(@RequestBody final UserPayment userPayment,
			final Principal principal) {
		userService.updateUserBilling(userServiceHelper.getUser(principal), userPayment);
		return new ResponseEntity<>(new ResponseMessage(MessageEnum.CHANGE_SUCCESS), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ResponseMessage> removePayment(@PathVariable final Long id, final Principal principal) {
		userPaymentService.removeById(id);
		return new ResponseEntity<>(new ResponseMessage(MessageEnum.REMOVE_SUCCESS), HttpStatus.OK);
	}

	@PutMapping()
	public ResponseEntity<ResponseMessage> setDefaultPayment(@RequestBody final Long id, final Principal principal) {
		userService.setUserDefaultPayment(id, userServiceHelper.getUser(principal));
		return new ResponseEntity<>(new ResponseMessage(MessageEnum.DEFAULT_SUCCESS), HttpStatus.OK);
	}

	@GetMapping()
	public List<UserPayment> getUserPaymentList(final Principal principal) {
		return userServiceHelper.getUser(principal).getUserPaymentList();
	}
}
