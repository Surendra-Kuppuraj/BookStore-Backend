/**
 * 
 */
package com.sk.bookstore.mail;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.sk.bookstore.domain.User;
import com.sk.bookstore.resource.constant.UserDatabaseFieldEnum;

/**
 * @author Surendra Kumar
 *
 */
public abstract class UserAccountEmailConstructor {
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	ServletContext servletContext;
	
	public abstract void sendNewUserRegistrationeEmail(final User user, final String password);

	public abstract void sendForgottenPasswordEmail(final User user, final String password);

	protected String setContext(final User user, final String password, final String imageResourceName, final String templateFileName) {
		Context context = new Context();
		context.setVariable(UserDatabaseFieldEnum.USER_NAME.fieldName(), user.getUsername());
		context.setVariable(UserDatabaseFieldEnum.PASSWORD.fieldName(), password);
		context.setVariable("imageResourceName", imageResourceName); // so that we can reference it from HTML

		return templateEngine.process(templateFileName, context);
	}	
}
