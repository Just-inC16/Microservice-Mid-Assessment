package com.tcs.library.monolithicLibraryManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.library.monolithicLibraryManagement.entity.Author;
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

	public Author getAuthor(int Id) {
		return authorRepository.getReferenceById(Id);
	}

	public Author addAuthor(Author newAuthor) {
		return authorRepository.saveAndFlush(newAuthor);
	}

	public Author updateAuthor(Integer Id, Author newAuthor) {
		Author foundAuthor = getAuthor(Id);
		// Set based on the newBook's values
		foundAuthor.setName(newAuthor.getName());
		foundAuthor.setCountry(newAuthor.getCountry());

		return authorRepository.saveAndFlush(foundAuthor);
	}

	public void deleteAuthor(int Id) {
		authorRepository.deleteById(Id);
	}
}
