/**
 * 
 */
package com.sk.bookstore.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.bookstore.domain.CartItem;
import com.sk.bookstore.domain.ShoppingCart;
import com.sk.bookstore.repository.ShoppingCartRepository;
import com.sk.bookstore.service.CartItemService;
import com.sk.bookstore.service.ShoppingCartService;

/**
 * @author Surendra Kumar
 *
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;

	private BigDecimal cartTotal = null;

	public ShoppingCart updateShoppingCart(ShoppingCart shoppingCart) {
	BigDecimal cartTotal = new BigDecimal(0);
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		
		for (CartItem cartItem : cartItemList) {
			if(cartItem.getBook().getInStockNumber()>0) {
				cartItemService.updateCartItem(cartItem);
				cartTotal = cartTotal.add(cartItem.getSubtotal());
			}
		}
		
		shoppingCart.setGrandTotal(cartTotal);
		
		shoppingCartRepository.save(shoppingCart);
		
		return shoppingCart;

//		Stream<CartItem> cartItemStream = cartItemService.findByShoppingCart(shoppingCart).stream();
//		cartItemStream.filter((cartItem) -> cartItem.getBook().getInStockNumber() > 0).forEach((cartItem) -> {
//			cartItemService.updateCartItem(cartItem);
//			cartTotal = cartTotal.add(cartItem.getSubtotal());
//		});
//		shoppingCart.setGrandTotal(cartTotal);
//		shoppingCartRepository.save(shoppingCart);
//		return shoppingCart;
	}

	public void clearShoppingCart(ShoppingCart shoppingCart) {
		Stream<CartItem> cartItemStream = cartItemService.findByShoppingCart(shoppingCart).stream();
		cartItemStream.forEach((cartItem) -> {
			cartItem.setShoppingCart(null);
			cartItemService.save(cartItem);
		});
		shoppingCart.setGrandTotal(new BigDecimal(0));
		shoppingCartRepository.save(shoppingCart);
	}
}
