package com.udemy.demo.book;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

	@GetMapping(value="/books")
	public ResponseEntity listBooks() {
		Book book = new Book();
		book.setTitle("Maths");
		book.setCategory(new Category("BD"));
		
		return new ResponseEntity(Arrays.asList(book), HttpStatus.OK);
	}
	
	@PostMapping(value="/books")
	public ResponseEntity addBook(@RequestBody Book book) {
		
		return new ResponseEntity(book, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value="/books/{bookId}")
	public ResponseEntity deleteBook(@PathVariable("bookId") String bookId) {
		
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping(value="/books/{bookId}")
	public ResponseEntity updateBook(@PathVariable("bookId") String bookId, @RequestBody Book book) {
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@GetMapping("/categories")
	public ResponseEntity listCategories () {
		Category category = new Category("BD");
		Category categoryRoman = new Category("Roman");
		
		return new ResponseEntity(Arrays.asList(category, categoryRoman), HttpStatus.OK);
	}
}
