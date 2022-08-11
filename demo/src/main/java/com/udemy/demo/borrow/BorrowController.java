package com.udemy.demo.borrow;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BorrowController {
	
	@GetMapping(value="/borrows")
	public ResponseEntity getMyBorrows() {
		Borrow borrow = new Borrow();
		borrow.setAskDate(LocalDate.now());
		return new ResponseEntity(borrow, HttpStatus.OK);
	}
	
	@PostMapping("/borrows/{bookId}")
	public ResponseEntity createBorrow(@PathVariable("bookId") String bookId) {
		
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/borrows/borrowId")
	public ResponseEntity deleteBorrow(@PathVariable("borrowId") String borrowId) {
		
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

}
