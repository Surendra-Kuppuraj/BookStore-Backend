package com.sk.bookstore.resource;

import java.security.Principal;
import java.util.HashMap;
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

import com.sk.bookstore.domain.Book;
import com.sk.bookstore.domain.CartItem;
import com.sk.bookstore.domain.ShoppingCart;
import com.sk.bookstore.domain.User;
import com.sk.bookstore.resource.constant.CheckoutDatabaseFieldEnum;
import com.sk.bookstore.resource.constant.MessageEnum;
import com.sk.bookstore.resource.util.ResponseMessage;
import com.sk.bookstore.resource.util.UserServiceHelper;
import com.sk.bookstore.service.BookService;
import com.sk.bookstore.service.CartItemService;
import com.sk.bookstore.service.ShoppingCartService;

/**
 * @author Surendra Kumar
 *
 */
@RestController
@RequestMapping("/cart")
public class ShoppingCartResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartResource.class);

	@Autowired
	private UserServiceHelper userServiceHelper;

	@Autowired
	private BookService bookService;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private ShoppingCartService shoppingCartService;

	@PostMapping
	public ResponseEntity<ResponseMessage> addItem(@RequestBody HashMap<String, String> mapper, Principal principal) {
		final String bookIdStr = (String) mapper.get(CheckoutDatabaseFieldEnum.BOOK_ID.fieldName());
		final String qtyStr = (String) mapper.get(CheckoutDatabaseFieldEnum.QTY.fieldName());
		User user = userServiceHelper.getUser(principal);
		try {
			long bookId = Long.parseLong(bookIdStr);
			int qty = Integer.parseInt(qtyStr);
			Book book = bookService.findOne(bookId);
			if (qty > book.getInStockNumber()) {
				return new ResponseEntity<>(new ResponseMessage(MessageEnum.NOT_ENOUGH_STOCK), HttpStatus.BAD_REQUEST);
			}
			cartItemService.addBookToCartItem(book, user, qty);
		} catch (NumberFormatException nfe) {
			final String errorDescription = "Invalid Argument has been found for either Book Id: " + bookIdStr
					+ " or for the Qty : " + qtyStr;
			LOGGER.error(errorDescription);
			throw new NumberFormatException(errorDescription);
		}
		return new ResponseEntity<>(new ResponseMessage(MessageEnum.BOOK_ADDED_SUCCESS), HttpStatus.OK);
	}

	@GetMapping("/items")
	public List<CartItem> getCartItemList(Principal principal) {
		final ShoppingCart shoppingCart = userServiceHelper.getUser(principal).getShoppingCart();
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		shoppingCartService.updateShoppingCart(shoppingCart);
		return cartItemList;
	}

	@GetMapping
	public ShoppingCart getShoppingCart(Principal principal) {
		final ShoppingCart shoppingCart = shoppingCartService
				.updateShoppingCart(userServiceHelper.getUser(principal).getShoppingCart());
		return shoppingCart;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseMessage> removeItem(@PathVariable Long id) {
		cartItemService.removeCartItem(cartItemService.findById(id));
		return new ResponseEntity<>(new ResponseMessage(MessageEnum.REMOVE_SUCCESS), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<ResponseMessage> updateCartItem(@RequestBody HashMap<String, String> mapper) {
		final String cartItemIdStr = mapper.get(CheckoutDatabaseFieldEnum.CART_ITEM_ID.fieldName());
		final String qtyStr = mapper.get(CheckoutDatabaseFieldEnum.QTY.fieldName());
		CartItem cartItem = null;
		try {
			long cartItemId = Long.parseLong(cartItemIdStr);
			int qty = Integer.parseInt(qtyStr);
			cartItem = cartItemService.findById(cartItemId);
			cartItem.setQty(qty);
			cartItem = cartItemService.updateCartItem(cartItem);
		} catch (NumberFormatException nfe) {
			final String errorDescription = "Invalid Argument has been found for either Cart item Id: " + cartItemIdStr
					+ " or for the Qty : " + qtyStr;
			LOGGER.error(errorDescription);
			throw new NumberFormatException(errorDescription);
		}
		return new ResponseEntity<>(new ResponseMessage(MessageEnum.UPDATE_SUCCESS), HttpStatus.OK);
	}
}
