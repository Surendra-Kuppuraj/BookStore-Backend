/**
 * 
 */
package com.sk.bookstore.resource;

import java.security.Principal;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.bookstore.config.MailConstructor;
import com.sk.bookstore.domain.BillingAddress;
import com.sk.bookstore.domain.Order;
import com.sk.bookstore.domain.Payment;
import com.sk.bookstore.domain.ShippingAddress;
import com.sk.bookstore.domain.ShoppingCart;
import com.sk.bookstore.domain.User;
import com.sk.bookstore.resource.util.UserServiceHelper;
import com.sk.bookstore.service.CartItemService;
import com.sk.bookstore.service.OrderService;
import com.sk.bookstore.service.ShoppingCartService;

/**
 * @author Surendra Kumar
 *
 */
@RestController
@RequestMapping("/checkout")
public class CheckoutResource {
	private Order order = new Order();

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private UserServiceHelper userServiceHelper;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private MailConstructor mailConstructor;

	@PostMapping
	public Order checkout(@RequestBody final HashMap<String, Object> mapper, final Principal principal) {
		ObjectMapper om = new ObjectMapper();

		ShippingAddress shippingAddress = om.convertValue(mapper.get("shippingAddress"), ShippingAddress.class);
		BillingAddress billingAddress = om.convertValue(mapper.get("billingAddress"), BillingAddress.class);
		Payment payment = om.convertValue(mapper.get("payment"), Payment.class);
		String shippingMethod = (String) mapper.get("shippingMethod");
		if(Objects.isNull(shippingAddress) || Objects.isNull(billingAddress) || Objects.isNull(payment) ) {
			throw new IllegalArgumentException("Invalid Argument has been specified either in shippingAddress or Billing Address or in Payment");
		}
		final User user = userServiceHelper.getUser(principal);
		final ShoppingCart shoppingCart = user.getShoppingCart();
		cartItemService.findByShoppingCart(shoppingCart);
		Order order = orderService.createOrder(shoppingCart, shippingAddress, billingAddress, payment, shippingMethod,
				user);
		mailSender.send(mailConstructor.constructOrderConfirmationEmail(user, order, Locale.ENGLISH));
		shoppingCartService.clearShoppingCart(shoppingCart);
		// this.order = order;
		return order;
	}

}
