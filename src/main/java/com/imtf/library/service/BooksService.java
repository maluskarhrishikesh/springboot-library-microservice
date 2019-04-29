package com.imtf.library.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.imtf.library.entity.BookMaster;
import com.imtf.library.exception.CustomExistsException;
import com.imtf.library.model.Book;
import com.imtf.library.model.StatusResponse;
import com.imtf.library.repository.BookRepository;

@Service
public class BooksService {

	@Autowired
	private BookRepository bookRepository;

	public ResponseEntity<?> saveBook(Book book) {
		int bookExists = bookRepository.checkBookNameExists(book.getBookName());
		if (bookExists != 0)
			throw new CustomExistsException("Book already exists");

		BookMaster bookMaster = new BookMaster();
		bookMaster.setBookName(book.getBookName());
		bookMaster.setBookPrice(book.getBookPrice());
		bookMaster.setBookQuantity(book.getBookQuantity());
		bookMaster.setBookDescription(book.getBookDescription());
		bookMaster.setImageUrl(book.getImageUrl());
		bookMaster.setDateOfPublication(book.getDateOfPublication());
		bookMaster.setCategory(book.getCategory());
		bookRepository.save(bookMaster);

		final StatusResponse response = new StatusResponse(HttpStatus.OK.value(), "SUCCESS");
		return new ResponseEntity<StatusResponse>(response, HttpStatus.OK);
	}

	public ResponseEntity<?> updateBook(Book book) {
		int bookExists = bookRepository.checkBookNameExistsEdit(book.getBookName(), book.getBookId());
		if (bookExists != 0)
			throw new CustomExistsException("Book already exists");

		BookMaster bookMaster = bookRepository.getOne(book.getBookId());
		bookMaster.setBookName(book.getBookName());
		bookMaster.setBookPrice(book.getBookPrice());
		bookMaster.setBookQuantity(book.getBookQuantity());
		bookMaster.setBookDescription(book.getBookDescription());
		bookMaster.setImageUrl(book.getImageUrl());
		bookMaster.setDateOfPublication(book.getDateOfPublication());
		bookMaster.setCategory(book.getCategory());
		bookRepository.save(bookMaster);

		final StatusResponse response = new StatusResponse(HttpStatus.OK.value(), "SUCCESS");
		return new ResponseEntity<StatusResponse>(response, HttpStatus.OK);
	}

	public ResponseEntity<?> deleteBook(long bookId) {
		BookMaster bookMaster = bookRepository.getOne(bookId);
		bookRepository.delete(bookMaster);

		final StatusResponse response = new StatusResponse(HttpStatus.OK.value(), "SUCCESS");
		return new ResponseEntity<StatusResponse>(response, HttpStatus.OK);
	}
	
	public Book selectBook(long bookId) {
		BookMaster bookMaster = bookRepository.selectBookDetails(bookId);
		Book selectedBook = new Book();
		selectedBook.setBookId(bookMaster.getBookId());
		selectedBook.setBookName(bookMaster.getBookName());
		selectedBook.setBookPrice(bookMaster.getBookPrice());
		selectedBook.setBookQuantity(bookMaster.getBookQuantity());
		selectedBook.setBookDescription(bookMaster.getBookDescription());
		selectedBook.setImageUrl(bookMaster.getImageUrl());
		selectedBook.setDateOfPublication(bookMaster.getDateOfPublication());
		selectedBook.setCategory(bookMaster.getCategory());

		return selectedBook;
	}

	public List<Book> selectBooksByCategory(int categoryId) {
		List<BookMaster> bookMaster = bookRepository.selectBooksByCategory(categoryId);
		List<Book> bookList = new ArrayList<>();

		for (BookMaster book : bookMaster) {
			Book selectedBook = new Book();
			selectedBook.setBookId(book.getBookId());
			selectedBook.setBookName(book.getBookName());
			selectedBook.setBookPrice(book.getBookPrice());
			selectedBook.setBookQuantity(book.getBookQuantity());
			selectedBook.setBookDescription(book.getBookDescription());
			selectedBook.setImageUrl(book.getImageUrl());
			selectedBook.setDateOfPublication(book.getDateOfPublication());
			selectedBook.setCategory(book.getCategory());
			bookList.add(selectedBook);
		}
		return bookList;
	}

	public List<BookMaster> selectAllBooks() {
		List<BookMaster> bookMaster = bookRepository.findAll();
		return bookMaster;
	}
}
