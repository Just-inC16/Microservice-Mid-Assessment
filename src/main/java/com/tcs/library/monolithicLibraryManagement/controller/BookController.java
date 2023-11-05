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

import com.tcs.library.monolithicLibraryManagement.entity.Book;
import com.tcs.library.monolithicLibraryManagement.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

	public BookService bookService;

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

//		Integer isbn = bookById.getISBN();
//		String name = bookById.getName();
//		String author = bookById.getAuthor();
//		int pages = bookById.getPages();
//		Book newBook = new Book(isbn, name, author, pages);
//		System.out.println(author);
//		System.out.println(name);
//		System.out.println(isbn);
//		System.out.println(pages);
		return ResponseEntity.ok(bookById);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Book> postBook(@RequestBody Book book) {
		Book savedBook = bookService.addBook(book);
		if (savedBook == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(savedBook);
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
