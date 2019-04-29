package com.imtf.library.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.imtf.library.exception.CustomExistsException;
import com.imtf.library.model.Book;
import com.imtf.library.model.StatusResponse;
import com.imtf.library.service.BooksService;

@RestController
public class BooksController {

	private static final Logger logger = LoggerFactory.getLogger(BooksController.class);

	@Autowired
	private BooksService bookService;

	@RequestMapping("/hello")
	public String sayHello() {
		return "Hi, Microservice is Up and Running";
	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/book/{bookId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getBookById(
			@RequestHeader(value = "Authorization", required = true) String authHeader, @PathVariable long bookId) {

		logger.info("[BooksController:getBookById] - Inside getBookById");
		try {
			return new ResponseEntity<>(bookService.selectBook(bookId), HttpStatus.OK);
		} catch (Exception ex) {
			final StatusResponse response = new StatusResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/book/category/{categoryId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getBooksByCategoryId(
			@RequestHeader(value = "Authorization", required = true) String authHeader, @PathVariable int categoryId) {

		logger.info("[BooksController:getBookById] - Inside getBooksByCategoryId="+categoryId);
		try {
			return new ResponseEntity<>(bookService.selectBooksByCategory(categoryId), HttpStatus.OK);
		} catch (Exception ex) {
			final StatusResponse response = new StatusResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/book", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllBooks(
			@RequestHeader(value = "Authorization", required = true) String authHeader) {

		logger.info("[BooksController:getBookById] - Inside getAllBooks");
		try {
			return new ResponseEntity<>(bookService.selectAllBooks(), HttpStatus.OK);
		} catch (Exception ex) {
			final StatusResponse response = new StatusResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/book", method = RequestMethod.POST)
	public ResponseEntity<?> saveBook(@RequestBody Book book,
			@RequestHeader(value = "Authorization", required = true) String authHeader) {

		logger.info("[BooksController:getBookById] - Inside saveBook");
		try {
			return bookService.saveBook(book);
		} catch (Exception ex) {
			if (ex instanceof CustomExistsException) {
				final StatusResponse response = new StatusResponse(HttpStatus.NOT_ACCEPTABLE.value(), ex.getMessage());
				return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
			}
			final StatusResponse response = new StatusResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/book", method = RequestMethod.PATCH)
	public ResponseEntity<?> updateBook(@RequestBody Book book,
			@RequestHeader(value = "Authorization", required = true) String authHeader) {

		logger.info("[BooksController:getBookById] - Inside updateBook");
		try {
			return bookService.updateBook(book);
		} catch (Exception ex) {
			if (ex instanceof CustomExistsException) {
				final StatusResponse response = new StatusResponse(HttpStatus.NOT_ACCEPTABLE.value(), ex.getMessage());
				return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
			}
			final StatusResponse response = new StatusResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/book/{bookId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteBook(
			@RequestHeader(value = "Authorization", required = true) String authHeader, @PathVariable long bookId) {

		logger.info("[BooksController:getBookById] - Inside deleteBook");
		try {
			return bookService.deleteBook(bookId);
		} catch (Exception ex) {
			final StatusResponse response = new StatusResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
