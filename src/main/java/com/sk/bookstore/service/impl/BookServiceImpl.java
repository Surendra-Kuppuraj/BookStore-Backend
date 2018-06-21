/**
 * 
 */
package com.sk.bookstore.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.bookstore.domain.Book;
import com.sk.bookstore.exception.function.DataBaseThrowingFunction;
import com.sk.bookstore.repository.BookRepository;
import com.sk.bookstore.service.BookService;

/**
 * @author Surendra Kumar
 *
 */
@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<Book> findAll() {
		List<Book> bookList = (List<Book>) bookRepository.findAll();
		return bookList.stream().filter(book -> book.isActive()).collect(Collectors.toList());
	}

	@Override
	public Book findOne(final Long id) {
		DataBaseThrowingFunction<Long, Book> userDataBaseThrowingFunction = (findByID) -> {
			return bookRepository.findOne(id);
		};
		return userDataBaseThrowingFunction.apply(id);
	}

	@Override
	public Book save(final Book book) {
		DataBaseThrowingFunction<Book, Book> userDataBaseThrowingFunction = (booktoSave) -> {
			return bookRepository.save(booktoSave);
		};
		return userDataBaseThrowingFunction.apply(book);
	}

	@Override
	public List<Book> blurrySearch(final String keyword) {
		DataBaseThrowingFunction<String, List<Book>> userDataBaseThrowingFunction = (searchKeyword) -> {
			return bookRepository.findByTitleContaining(searchKeyword);
		};
		List<Book> bookList = userDataBaseThrowingFunction.apply(keyword);
		return bookList.stream().filter(book -> book.isActive()).collect(Collectors.toList());
	}

	@Override
	public void removeOne(final Long id) {
		bookRepository.delete(id);
	}

}
