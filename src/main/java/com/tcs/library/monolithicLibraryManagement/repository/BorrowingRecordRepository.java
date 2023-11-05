package com.tcs.library.monolithicLibraryManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.library.monolithicLibraryManagement.entity.BorrowingRecord;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Integer> {

}
