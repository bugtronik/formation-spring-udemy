package com.udemy.demo.borrow;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.demo.book.Book;
import com.udemy.demo.book.BookController;
import com.udemy.demo.book.BookRepository;
import com.udemy.demo.book.BookStatus;
import com.udemy.demo.user.UserInfo;
import com.udemy.demo.user.UserRepository;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class BorrowController {
	
	@Autowired
	BorrowRepository borrowRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BookController bookController;
	
	@GetMapping(value="/borrows")
	public ResponseEntity getMyBorrows(Principal principal) {
		List<Borrow> borrows = borrowRepository.findByBorrowerId(bookController.getUserConnectId(principal));
		return new ResponseEntity(borrows, HttpStatus.OK);
	}
	
	@PostMapping("/borrows/{bookId}")
	public ResponseEntity createBorrow(@PathVariable("bookId") String bookId, Principal principal) {
		
		Integer userConnectedId = bookController.getUserConnectId(principal);
		Optional<UserInfo> borrower = userRepository.findById(userConnectedId);
		Optional<Book> book = bookRepository.findById(Integer.valueOf(bookId));
		
		if(borrower.isPresent() && book.isPresent() && book.get().getStatus().equals(BookStatus.FREE)) {
			Borrow borrow = new Borrow();
			borrow.setBook(book.get());
			borrow.setBorrower(borrower.get());
			borrow.setLender(book.get().getUser());
			borrow.setAskDate(LocalDate.now());
			borrowRepository.save(borrow);
			
			book.get().setStatus(BookStatus.BORROWED);
			bookRepository.save(book.get());
			return new ResponseEntity(HttpStatus.CREATED);
		}
		
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/borrows/borrowId")
	public ResponseEntity deleteBorrow(@PathVariable("borrowId") String borrowId) {
		
		Optional<Borrow> borrow = borrowRepository.findById(Integer.valueOf(borrowId));
		if(borrow.isEmpty()) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
		Borrow borrowEntity = borrow.get();
		borrowEntity.setCloseDate(LocalDate.now());
		borrowRepository.save(borrowEntity);
		
		Book book = borrowEntity.getBook();
		book.setStatus(BookStatus.FREE);
		bookRepository.save(book);
		
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

}
