/**
 * 
 */
package com.sk.bookstore.resource;

import java.security.Principal;
import java.util.HashMap;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.bookstore.domain.BillingAddress;
import com.sk.bookstore.domain.Order;
import com.sk.bookstore.domain.Payment;
import com.sk.bookstore.domain.ShippingAddress;
import com.sk.bookstore.domain.ShoppingCart;
import com.sk.bookstore.domain.User;
import com.sk.bookstore.mail.OrderConfirmationMail;
import com.sk.bookstore.resource.constant.CheckoutDatabaseFieldEnum;
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

	@Autowired
	private UserServiceHelper userServiceHelper;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private Environment environment;

	@Autowired
	@Qualifier(value = "javaOrderConfirmationMail")
	private OrderConfirmationMail javaOrderConfirmationMail;

	@Autowired
	@Qualifier(value = "sendGridOrderConfiramtionMail")
	private OrderConfirmationMail sendGridOrderConfirmationMail;

	@PostMapping
	public Order checkout(@RequestBody final HashMap<String, Object> mapper, final Principal principal) {
		ObjectMapper om = new ObjectMapper();

		ShippingAddress shippingAddress = om.convertValue(mapper.get(CheckoutDatabaseFieldEnum.SHIPPING_ADDRESS.fieldName()), ShippingAddress.class);
		BillingAddress billingAddress = om.convertValue(mapper.get(CheckoutDatabaseFieldEnum.BILLING_ADDRESS.fieldName()), BillingAddress.class);
		Payment payment = om.convertValue(mapper.get(CheckoutDatabaseFieldEnum.PAYMENT.fieldName()), Payment.class);
		String shippingMethod = (String) mapper.get(CheckoutDatabaseFieldEnum.SHIPPING_METHOD.fieldName());
		if (Objects.isNull(shippingAddress) || Objects.isNull(billingAddress) || Objects.isNull(payment)) {
			throw new IllegalArgumentException(
					"Invalid Argument has been specified either in shippingAddress or Billing Address or in Payment");
		}
		final User user = userServiceHelper.getUser(principal);
		final ShoppingCart shoppingCart = user.getShoppingCart();
		cartItemService.findByShoppingCart(shoppingCart);
		Order order = orderService.createOrder(shoppingCart, shippingAddress, billingAddress, payment, shippingMethod,
				user);
		final String applicationMode = environment.getProperty("spring.profiles.active");
		if ("dev".equals(applicationMode)) {
			javaOrderConfirmationMail.sendOrderConfirmationEmail(user, order);
		}
		// For Production environment.
		if ("production".equals(applicationMode)) {
			sendGridOrderConfirmationMail.sendOrderConfirmationEmail(user, order);
		}
		shoppingCartService.clearShoppingCart(shoppingCart);
		return order;
	}
}
