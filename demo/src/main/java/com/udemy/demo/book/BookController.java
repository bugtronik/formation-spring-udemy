package com.udemy.demo.book;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;

	@GetMapping(value="/books")
	public ResponseEntity listBooks(@RequestParam(required = false) BookStatus status) {
		
		Integer userConnectedId = this.getUserConnectId();
		List<Book> books;
		
		if(status != null && status == BookStatus.FREE) {
			books = bookRepository.findByStatusAndUserIdNotAndDeletedFalse(status, userConnectedId);
		} else {
			books = bookRepository.findByUserIdAndDeletedFalse(userConnectedId);
		}
		
		return new ResponseEntity(books, HttpStatus.OK);
	}
	
	private Integer getUserConnectId() {
		// TODO Auto-generated method stub
		return 1;
	}

	@PostMapping(value="/books")
	public ResponseEntity addBook(@RequestBody @Valid Book book) {
		
		return new ResponseEntity(book, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value="/books/{bookId}")
	public ResponseEntity deleteBook(@PathVariable("bookId") String bookId) {
		
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping(value="/books/{bookId}")
	public ResponseEntity updateBook(@PathVariable("bookId") String bookId, @RequestBody @Valid Book book) {
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@GetMapping("/categories")
	public ResponseEntity listCategories () {
		Category category = new Category("BD");
		Category categoryRoman = new Category("Roman");
		
		return new ResponseEntity(Arrays.asList(category, categoryRoman), HttpStatus.OK);
	}
}
