/**
 * 
 */
package com.sk.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.sk.bookstore.domain.NewsLetter;

/**
 * @author Surendra Kumar
 *
 */
public interface NewsLetterRepository extends CrudRepository<NewsLetter, Long> {
	NewsLetter findByEmailAddress(String emailAddress);
	void deleteByEmailAddress(String emailAddress);

}