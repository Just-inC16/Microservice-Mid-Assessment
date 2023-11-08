package com.tcs.library.monolithicLibraryManagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.library.monolithicLibraryManagement.entity.Book;
import com.tcs.library.monolithicLibraryManagement.entity.BookStatus;
import com.tcs.library.monolithicLibraryManagement.entity.BorrowingRecord;
import com.tcs.library.monolithicLibraryManagement.repository.BookRepository;
import com.tcs.library.monolithicLibraryManagement.repository.BorrowingRecordRepository;

@Service
public class BorrowingRecordService {
	private BorrowingRecordRepository borrowingRecordRepository;
	private BookRepository bookRepository;
	private BookService bookService;

	@Autowired
	public BorrowingRecordService(BorrowingRecordRepository borrowingRecordRepository, BookService bookService,
			BookRepository bookRepository) {
		this.borrowingRecordRepository = borrowingRecordRepository;
		this.bookService = bookService;
		this.bookRepository = bookRepository;
	}

	public String borrowBook(Integer Id, BorrowingRecord borrowingRecord) {
		Book bookToBorrow = bookService.getBook(Id);
		bookToBorrow.setStatus(BookStatus.BORROWED);
		bookRepository.save(bookToBorrow);

		borrowingRecord.setBook(bookToBorrow);
		borrowingRecordRepository.save(borrowingRecord);
		return "Borrowed the book";
	}

	public String returnBook(Integer Id) {
		Book bookToBorrow = bookService.getBook(Id);
		bookToBorrow.setStatus(BookStatus.AVAILABLE);
		bookRepository.save(bookToBorrow);

		// For each borrowed book, delete the record with the matching id
		List<BorrowingRecord> allBorrowBooks = getAllBorrowRecords();
		for (BorrowingRecord borrowing : allBorrowBooks) {
			Long currentBookId = borrowing.getBook().getId();
			if (currentBookId == (long) Id) {
				borrowingRecordRepository.delete(borrowing);
			}
		}

		return "Returned the book";
	}

	public List<BorrowingRecord> getAllBorrowRecords() {
		return borrowingRecordRepository.findAll();
	}

	public List<Book> getBorrowBookByUser(String user) {
//		System.out.println(user);
//		for (Book b : borrowingRecordRepository.findBorrowBooksByUser(user)) {
//			System.out.println(b.getAuthor());
//			System.out.println(b.getISBN());
//			System.out.println(b.getTitle());
//			System.out.println(b.getStatus());
//
//		}
//		System.out.println(borrowingRecordRepository.findBorrowBooksByUser(user).get(0));
//		System.out.println();
		List<Book> allBookByUser = new ArrayList<Book>();
		List<BorrowingRecord> allBorrowBooks = getAllBorrowRecords();
		for (BorrowingRecord borrowing : allBorrowBooks) {
			String currentUser = borrowing.getUser();
			Book currentBook = borrowing.getBook();
			if (currentUser.equals(user)) {
				allBookByUser.add(currentBook);
			}
		}
		return allBookByUser;
	}
}
