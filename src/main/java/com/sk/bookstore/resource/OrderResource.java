package com.sk.bookstore.resource;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.bookstore.domain.Order;
import com.sk.bookstore.resource.util.UserServiceHelper;

@RestController
@RequestMapping("/order")
public class OrderResource {

	@Autowired
	private UserServiceHelper userServiceHelper;

	@GetMapping
	public List<Order> getOrderList(Principal principal) {
		return userServiceHelper.getUser(principal).getOrderList();
	}

}
