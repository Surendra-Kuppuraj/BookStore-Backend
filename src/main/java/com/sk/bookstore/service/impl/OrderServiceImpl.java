/**
 * 
 */
package com.sk.bookstore.service.impl;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.bookstore.domain.BillingAddress;
import com.sk.bookstore.domain.Book;
import com.sk.bookstore.domain.CartItem;
import com.sk.bookstore.domain.Order;
import com.sk.bookstore.domain.Payment;
import com.sk.bookstore.domain.ShippingAddress;
import com.sk.bookstore.domain.ShoppingCart;
import com.sk.bookstore.domain.User;
import com.sk.bookstore.exception.function.DataBaseThrowingFunction;
import com.sk.bookstore.repository.OrderRepository;
import com.sk.bookstore.service.CartItemService;
import com.sk.bookstore.service.OrderService;

/**
 * @author Surendra Kumar
 *
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CartItemService cartItemService;

	public synchronized Order createOrder(final ShoppingCart shoppingCart, final ShippingAddress shippingAddress,
			final BillingAddress billingAddress, final Payment payment, final String shippingMethod, final User user) {
		final Order order = new Order();
		order.setBillingAddress(billingAddress);
		order.setOrderStatus("created");
		order.setPayment(payment);
		order.setShippingAddress(shippingAddress);
		order.setShippingMethod(shippingMethod);

		final List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		cartItemList.forEach((CartItem cartItem) -> {
			Book book = cartItem.getBook();
			cartItem.setOrder(order);
			book.setInStockNumber(book.getInStockNumber() - cartItem.getQty());
		});

		
		final LocalDate today = LocalDate.now();
		LocalDate estimatedDeliveryDate = null;
		if (shippingMethod.equals("groundShipping")) {
			estimatedDeliveryDate = today.plusDays(5);
		} else {
			estimatedDeliveryDate = today.plusDays(3);
		}
		order.setOrderDate(today);
		order.setShippingDate(estimatedDeliveryDate);
		order.setCartItemList(cartItemList);
		order.setOrderTotal(shoppingCart.getGrandTotal());	
		
		shippingAddress.setOrder(order);
		billingAddress.setOrder(order);
		payment.setOrder(order);
		order.setUser(user);
		return orderRepository.save(order);
	}

	public Order findOne(final Long id) {
		DataBaseThrowingFunction<Long, Order> userDataBaseThrowingFunction = (findByID) -> {
			return orderRepository.findOne(findByID);
		};
		return userDataBaseThrowingFunction.apply(id);
	}

}
