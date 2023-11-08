package com.tcs.library.monolithicLibraryManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tcs.library.monolithicLibraryManagement.entity.Book;
import com.tcs.library.monolithicLibraryManagement.entity.BorrowingRecord;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Integer> {
	@Query("SELECT b.book FROM BorrowingRecord b where b.user=:user")
	List<Book> findBorrowBooksByUser(@Param("user") String user);

	@Modifying
	@Query("DELETE FROM BorrowingRecord WHERE id = (SELECT id FROM Book WHERE id = :id)")
	void returnBorrowedBook(@Param("id") Integer id);
//	void returnBorrowedBook(Book book);
}
