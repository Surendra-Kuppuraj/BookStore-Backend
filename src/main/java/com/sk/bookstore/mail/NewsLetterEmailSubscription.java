/**
 * 
 */
package com.sk.bookstore.mail;

import com.sk.bookstore.domain.NewsLetter;

/**
 * @author Surendra Kumar
 *
 */
public interface NewsLetterEmailSubscription {

	void sendNewsLetterEmailSubscription(NewsLetter newsLetter);

}