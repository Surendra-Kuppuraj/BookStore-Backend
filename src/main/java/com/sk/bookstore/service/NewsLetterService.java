/**
 * 
 */
package com.sk.bookstore.service;

import java.util.List;

import com.sk.bookstore.domain.NewsLetter;

/**
 * @author Surendra Kumar
 *
 */
public interface NewsLetterService {
	void deleteByEmailAddress(String emailAddress);

	NewsLetter findByEmailAddress(String emailAddress);

	List<NewsLetter> findAll();
	
	NewsLetter save(NewsLetter newsLetter);
}
