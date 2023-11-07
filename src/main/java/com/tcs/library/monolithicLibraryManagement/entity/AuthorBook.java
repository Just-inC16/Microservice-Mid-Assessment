package com.tcs.library.monolithicLibraryManagement.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthorBook {
	private Author author;
	private List<Book> books;
}
