/**
 * 
 */
package com.sk.bookstore.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.sk.bookstore.domain.Order;
import com.sk.bookstore.domain.User;
import com.sk.bookstore.resource.constant.CheckoutDatabaseFieldEnum;
import com.sk.bookstore.resource.constant.UserDatabaseFieldEnum;

/**
 * @author Surendra Kumar
 *
 */
public abstract class OrderConfirmationMail {

	@Autowired
	private TemplateEngine templateEngine;

	abstract public void sendOrderConfirmationEmail(final User user, final Order order);

	protected String setContext(final User user, final Order order, final String templateFileName) {
		Context context = new Context();
		context.setVariable(CheckoutDatabaseFieldEnum.ORDER.fieldName(), order);
		context.setVariable(UserDatabaseFieldEnum.USER.fieldName(), user);
		context.setVariable(CheckoutDatabaseFieldEnum.CART_ITEM_LIST.fieldName(), order.getCartItemList());
		return templateEngine.process(templateFileName, context);
	}
}