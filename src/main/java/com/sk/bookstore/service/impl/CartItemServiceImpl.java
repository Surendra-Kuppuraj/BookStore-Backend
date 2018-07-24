/**
 * 
 */
package com.sk.bookstore.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.bookstore.domain.Book;
import com.sk.bookstore.domain.BookToCartItem;
import com.sk.bookstore.domain.CartItem;
import com.sk.bookstore.domain.ShoppingCart;
import com.sk.bookstore.domain.User;
import com.sk.bookstore.exception.function.DataBaseThrowingFunction;
import com.sk.bookstore.repository.BookToCartItemRepository;
import com.sk.bookstore.repository.CartItemRepository;
import com.sk.bookstore.service.CartItemService;

/**
 * @author Surendra Kumar
 *
 */
@Service
@Transactional
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private BookToCartItemRepository bookToCartItemRepository;

	public CartItem addBookToCartItem(Book book, User user, int qty) {
		final List<CartItem> cartItemList = findByShoppingCart(user.getShoppingCart());

		// For existing cartItem to update.
		final Function<CartItem, CartItem> cartItemMapper = (CartItem cartItem) -> {
			cartItem.setQty(cartItem.getQty() + qty);
			cartItem.setSubtotal(new BigDecimal(book.getOurPrice()).multiply(new BigDecimal(qty)));
			return this.save(cartItem);
		};
		Optional<CartItem> optionalCartItem = cartItemList.stream()
				.filter((cartItem) -> book.getId() == cartItem.getBook().getId()).map(cartItemMapper).findFirst();
		if (optionalCartItem.isPresent()) {
			return optionalCartItem.get();
		}
		
		// New cartItem when there is no book item in the user's shopping cart.
		CartItem cartItem = new CartItem();
		cartItem.setShoppingCart(user.getShoppingCart());
		cartItem.setBook(book);
		cartItem.setQty(qty);
		cartItem.setSubtotal(new BigDecimal(book.getOurPrice()).multiply(new BigDecimal(qty)));
		cartItem = this.save(cartItem);

		final BookToCartItem bookToCartItem = new BookToCartItem();
		bookToCartItem.setBook(book);
		bookToCartItem.setCartItem(cartItem);
		saveBookToCartItem(bookToCartItem);
		return cartItem;
	}

	public void removeCartItem(CartItem cartItem) {
		bookToCartItemRepository.deleteByCartItem(cartItem);
		cartItemRepository.delete(cartItem);
	}

	public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart) {
		DataBaseThrowingFunction<ShoppingCart, List<CartItem>> userDataBaseThrowingFunction = (shoppingCartArg) -> {
			return cartItemRepository.findByShoppingCart(shoppingCartArg);
		};
		return userDataBaseThrowingFunction.apply(shoppingCart);
	}

	public CartItem updateCartItem(CartItem cartItem) {
		BigDecimal bigDecimal = new BigDecimal(cartItem.getBook().getOurPrice())
				.multiply(new BigDecimal(cartItem.getQty()));
		bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
		cartItem.setSubtotal(bigDecimal);
		return save(cartItem);
	}

	public CartItem findById(Long id) {
		DataBaseThrowingFunction<Long, CartItem> userDataBaseThrowingFunction = (idArg) -> {
			return cartItemRepository.findOne(idArg);
		};
		return userDataBaseThrowingFunction.apply(id);
	}

	public CartItem save(CartItem cartItem) {
		DataBaseThrowingFunction<CartItem, CartItem> userDataBaseThrowingFunction = (saveCartItem) -> {
			return cartItemRepository.save(saveCartItem);
		};
		return userDataBaseThrowingFunction.apply(cartItem);
	}

	private BookToCartItem saveBookToCartItem(BookToCartItem bookToCartItem) {
		DataBaseThrowingFunction<BookToCartItem, BookToCartItem> userDataBaseThrowingFunction = (
				saveBookToCartItem) -> {
			return bookToCartItemRepository.save(saveBookToCartItem);
		};
		return userDataBaseThrowingFunction.apply(bookToCartItem);
	}
}
