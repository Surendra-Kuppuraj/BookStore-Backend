/**
 * 
 */
package com.sk.bookstore.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.bookstore.domain.NewsLetter;
import com.sk.bookstore.exception.function.DataBaseThrowingFunction;
import com.sk.bookstore.repository.NewsLetterRepository;
import com.sk.bookstore.service.NewsLetterService;

/**
 * @author Surendra Kumar
 *
 */
@Service
@Transactional
public class NewsLetterServiceImpl implements NewsLetterService {

	@Autowired
	private NewsLetterRepository newsLetterRepository;

	@Override
	public void deleteByEmailAddress(final String emailAddress) {
		newsLetterRepository.deleteByEmailAddress(emailAddress);
	}

	@Override
	public NewsLetter findByEmailAddress(final String emailAddress) {
		return newsLetterRepository.findByEmailAddress(emailAddress);
	}

	@Override
	public List<NewsLetter> findAll() {
		List<NewsLetter> bookList = (List<NewsLetter>) newsLetterRepository.findAll();
		return bookList;
	}

	@Override
	public NewsLetter save(final NewsLetter newsLetter) {
		DataBaseThrowingFunction<NewsLetter, NewsLetter> userDataBaseThrowingFunction = (newsLetterToSave) -> {
			return newsLetterRepository.save(newsLetterToSave);
		};
		return userDataBaseThrowingFunction.apply(newsLetter);
	}
}
