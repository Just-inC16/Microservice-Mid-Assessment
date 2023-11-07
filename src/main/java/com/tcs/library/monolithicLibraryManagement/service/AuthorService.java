package com.tcs.library.monolithicLibraryManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.library.monolithicLibraryManagement.entity.Author;
import com.tcs.library.monolithicLibraryManagement.entity.AuthorBook;
import com.tcs.library.monolithicLibraryManagement.entity.Book;
import com.tcs.library.monolithicLibraryManagement.repository.AuthorRepository;

@Service
public class AuthorService {
	private AuthorRepository authorRepository;

	@Autowired
	public AuthorService(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	public List<Author> getAuthors() {
		return authorRepository.findAll();
	}

	public Author getAuthor(Long Id) {
		return authorRepository.getReferenceById(Id);
	}

	public Author addAuthor(AuthorBook authorBook) {
		Author newAuthor = new Author();

		// Add the author first
		newAuthor.setId(authorBook.getAuthor().getId());
		newAuthor.setCountry(authorBook.getAuthor().getCountry());
		newAuthor.setName(authorBook.getAuthor().getName());
		newAuthor = authorRepository.saveAndFlush(newAuthor);
		System.out.println(newAuthor.toString());

		List<Book> newBooks = authorBook.getBooks();
		for (Book currBook : newBooks) {
			currBook.setAuthor(newAuthor);
			System.out.println(currBook.toString());
		}
		newAuthor.setBooks(newBooks);
		return authorRepository.save(newAuthor);
	}

	public Author updateAuthor(Long Id, Author newAuthor) {
		Author foundAuthor = getAuthor(Id);
		// Set based on the newAuthor's values
		foundAuthor.setName(newAuthor.getName());
		foundAuthor.setCountry(newAuthor.getCountry());
		foundAuthor.setBooks(newAuthor.getBooks());

		return authorRepository.saveAndFlush(foundAuthor);
	}

	public void deleteAuthor(Long Id) {
		authorRepository.deleteById(Id);
	}

	public List<Book> getAllBooksForAuthor(Long Id) {
		Author authorById = getAuthor(Id);
		return authorById.getBooks();

	}
}
