package com.sk.bookstore.resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

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
import com.sk.bookstore.resource.constant.MessageEnum;
import com.sk.bookstore.resource.util.ResponseMessage;
import com.sk.bookstore.service.BookService;

/**
 * @author Surendra Kumar
 *
 */
@RestController
@RequestMapping("/book")
public class BookResource {

	private static final String BOOK_IMAGE_STORAGE_PATH = "src/main/resources/static/image/book/";
	private static final String IMAGE_FILE_EXTENSTION = ".png";

	@Autowired
	private BookService bookService;

	@PostMapping
	public ResponseEntity<ResponseMessage> addBook(@RequestBody final Book book) {
		 if(!Objects.isNull(bookService.save(book))){
			return new ResponseEntity<>(new ResponseMessage(MessageEnum.BOOK_ADDED_SUCCESS), HttpStatus.OK);
		 }
		 return new ResponseEntity<>(new ResponseMessage(MessageEnum.BOOK_ADDED_FAILED), HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping
	public ResponseEntity<ResponseMessage> deleteBook(@RequestBody final Long id) throws IOException {
		bookService.removeOne(id);
		final boolean isFileDeleted = Files.deleteIfExists(Paths.get(BOOK_IMAGE_STORAGE_PATH + id + IMAGE_FILE_EXTENSTION));
		if(!isFileDeleted) {
			return new ResponseEntity<>(new ResponseMessage(MessageEnum.REMOVE_FAILED), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new ResponseMessage(MessageEnum.REMOVE_SUCCESS), HttpStatus.OK);
	}

	@GetMapping()
	public List<Book> getBookList() {
		return bookService.findAll();
	}

	@GetMapping("/{id}")
	public Book getBook(@PathVariable final Long id) {
		return bookService.findOne(id);
	}

	@PostMapping("/searchBook")
	public List<Book> searchBook(@RequestBody final String keyword) {
		return bookService.blurrySearch(keyword);
	}

	@PutMapping
	public Book updateBook(@RequestBody final Book book) {
		return bookService.save(book);
	}
}
