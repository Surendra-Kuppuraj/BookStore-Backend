package com.sk.bookstore.service;

import java.util.List;

import com.sk.bookstore.domain.Book;
import com.sk.bookstore.domain.CartItem;
import com.sk.bookstore.domain.ShoppingCart;
import com.sk.bookstore.domain.User;

public interface CartItemService {
	CartItem addBookToCartItem(Book book, User user, int qty);

	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);

	// List<CartItem> findByOrder(Order order);

	CartItem updateCartItem(CartItem cartItem);

	void removeCartItem(CartItem cartItem);

	CartItem findById(Long id);

	CartItem save(CartItem cartItem);
}
