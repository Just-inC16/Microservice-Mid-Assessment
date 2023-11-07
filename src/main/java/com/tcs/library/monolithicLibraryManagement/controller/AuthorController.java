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
import com.tcs.library.monolithicLibraryManagement.entity.AuthorBook;
import com.tcs.library.monolithicLibraryManagement.entity.Book;
import com.tcs.library.monolithicLibraryManagement.service.AuthorService;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
	private AuthorService authorService;

	@Autowired
	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}

	@GetMapping("")
	public List<Author> getAuthors() {
		List<Author> allAuthors = authorService.getAuthors();
		return allAuthors;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Author> getAuthor(@PathVariable("id") Long id) {
		Author authorById = authorService.getAuthor(id);
		if (authorById == null) {
			return ResponseEntity.notFound().build();
		}

		String name = authorById.getName();
		String country = authorById.getCountry();
		List<Book> books = authorById.getBooks();
		Author newAuthor = new Author(id, name, country, books);
//		System.out.println(author);
//		System.out.println(name);
//		System.out.println(isbn);
//		System.out.println(pages);
		return ResponseEntity.ok(newAuthor);
	}

//	@RequestMapping(value = "", method = RequestMethod.POST)
//	public ResponseEntity<Author> postAuthor(@RequestBody Author author) {
//
//		Author savedAuthor = authorService.addAuthor(author);
//		if (savedAuthor == null) {
//			return ResponseEntity.notFound().build();
//		}
//		return ResponseEntity.ok(savedAuthor);
//	}
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Author> postAuthor(@RequestBody AuthorBook authorBook) {

		Author savedAuthor = authorService.addAuthor(authorBook);
		System.out.println(savedAuthor.toString());
		if (savedAuthor == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(savedAuthor);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Author> updateBook(@PathVariable("id") Long Id, @RequestBody Author author) {
		Author updateAuthor = authorService.updateAuthor(Id, author);
		if (updateAuthor == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updateAuthor);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAuthor(@PathVariable("id") Long Id) {
		try {
			authorService.deleteAuthor(Id);
			return ResponseEntity.ok("Successful deletion");
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.notFound().build();
		}

	}

	@GetMapping("/allBooks/{id}")
	public ResponseEntity<List<Book>> getAllBooksForAuthor(@PathVariable("id") Long Id) {

		return ResponseEntity.ok(authorService.getAllBooksForAuthor(Id));

	}

	@GetMapping("/hello")
	void helloWorld() {
		System.out.println("Hello World");
	}
}
