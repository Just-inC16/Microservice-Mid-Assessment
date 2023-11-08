package com.tcs.library.monolithicLibraryManagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.library.monolithicLibraryManagement.entity.BorrowingRecord;
import com.tcs.library.monolithicLibraryManagement.service.BorrowingRecordService;

@RestController
@RequestMapping("/api/borrowing")
public class BorrowingBookController {
	public BorrowingRecordService borrowingRecordService;

	public BorrowingBookController(BorrowingRecordService borrowingRecordService) {
		this.borrowingRecordService = borrowingRecordService;
	}

	@PutMapping("/borrow/{book_id}")
	public String borrowBook(@PathVariable("book_id") String Id, @RequestBody BorrowingRecord borrowingRecord) {
		return borrowingRecordService.borrowBook(Integer.parseInt(Id), borrowingRecord);
	}

	@PutMapping("/return/{book_id}")
	public String returnBook(@PathVariable("book_id") String Id) {
		return borrowingRecordService.returnBook(Integer.parseInt(Id));
	}

	@GetMapping("/{user}")
	public void getBorrowBookByUser(@PathVariable("user") String user) {
		borrowingRecordService.getBorrowBookByUser(user);
	}
}
