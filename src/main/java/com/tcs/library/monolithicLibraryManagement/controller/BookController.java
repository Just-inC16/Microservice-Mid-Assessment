package com.tcs.library.monolithicLibraryManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.library.monolithicLibraryManagement.entity.Author;
import com.tcs.library.monolithicLibraryManagement.entity.Book;
import com.tcs.library.monolithicLibraryManagement.entity.BookStatus;
import com.tcs.library.monolithicLibraryManagement.service.AuthorService;
import com.tcs.library.monolithicLibraryManagement.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

	public BookService bookService;
	public AuthorService authorService;

	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping("")
	public List<Book> getBooks() {
		List<Book> allBooks = bookService.getBooks();
		return allBooks;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
		Book bookById = bookService.getBook(id);
		if (bookById == null) {
			return ResponseEntity.notFound().build();
		}
		String title = bookById.getTitle();
		String isbn = bookById.getISBN();
		Author author = bookById.getAuthor();
		BookStatus status = bookById.getStatus();
		Book newBook = new Book(Long.valueOf(id), title, isbn, author, status);
//		System.out.println(author);
//		System.out.println(name);
//		System.out.println(isbn);
//		System.out.println(pages);
		return ResponseEntity.ok(bookById);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public ResponseEntity<String> postBook(@PathVariable("id") Long author_id, @RequestBody Book book) {
		String msg = bookService.addBook(author_id, book);
		if (msg == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(msg);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable("id") Integer Id, @RequestBody Book book) {
		Book updateBook = bookService.updateBook(Id, book);
		if (updateBook == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updateBook);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable("id") int Id) {
		try {
			bookService.deleteBook(Id);
			return ResponseEntity.ok("Successful deletion");
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.notFound().build();
		}

	}
}
