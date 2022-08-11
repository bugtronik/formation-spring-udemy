package com.udemy.demo.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@PostMapping(value="/users")
	public ResponseEntity addUser(@RequestBody User user) {
		User userOld = new User("test@test.com");
		
		return new ResponseEntity(userOld, HttpStatus.CREATED);
	}
}
