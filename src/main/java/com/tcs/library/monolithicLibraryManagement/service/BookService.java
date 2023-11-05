package com.tcs.library.monolithicLibraryManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.library.monolithicLibraryManagement.entity.Book;
import com.tcs.library.monolithicLibraryManagement.repository.BookRepository;

@Service
public class BookService {

	private BookRepository bookRepository;

	@Autowired
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public List<Book> getBooks() {
		return bookRepository.findAll();
	}

	public Book getBook(int Id) {
		return bookRepository.getReferenceById(Id);
	}

	public Book addBook(Book newBook) {
		return bookRepository.saveAndFlush(newBook);
	}

	public Book updateBook(Integer Id, Book newBook) {
		Book foundBook = getBook(Id);
		// Set based on the newBook's values
		foundBook.setTitle(newBook.getTitle());
		foundBook.setISBN(newBook.getISBN());
		foundBook.setAuthor(newBook.getAuthor());

		return bookRepository.saveAndFlush(foundBook);
	}

	public void deleteBook(int Id) {
		bookRepository.deleteById(Id);
	}
}
