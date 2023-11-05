package com.tcs.library.monolithicLibraryManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.library.monolithicLibraryManagement.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
