package com.tcs.library.monolithicLibraryManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.library.monolithicLibraryManagement.entity.Author;
import com.tcs.library.monolithicLibraryManagement.entity.Book;
import com.tcs.library.monolithicLibraryManagement.repository.AuthorRepository;
import com.tcs.library.monolithicLibraryManagement.repository.BookRepository;

@Service
public class BookService {

	private BookRepository bookRepository;
	private AuthorRepository authorRepository;

	@Autowired
	public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
	}

	public List<Book> getBooks() {
		return bookRepository.findAll();
	}

	public Book getBook(int Id) {
		return bookRepository.getReferenceById(Id);
	}

	public String addBook(Long Id, Book newBook) {
		Author authorById = authorRepository.getReferenceById(Id);
		authorById.getBooks().add(newBook);

		newBook.setAuthor(authorById);
		bookRepository.save(newBook);
//		System.out.println(authorBook.toString());
//		System.out.println(authorId);
		return "Successfully added";
//		return bookRepository.save(newBook);
	}

	public Book updateBook(Integer Id, Book newBook) {
		Book foundBook = getBook(Id);
		// Set based on the newBook's values
		foundBook.setTitle(newBook.getTitle());
		foundBook.setISBN(newBook.getISBN());
		foundBook.setAuthor(newBook.getAuthor());
		foundBook.setStatus(newBook.getStatus());

		return bookRepository.saveAndFlush(foundBook);
	}

	public void deleteBook(int Id) {
		bookRepository.deleteById(Id);
	}
}
