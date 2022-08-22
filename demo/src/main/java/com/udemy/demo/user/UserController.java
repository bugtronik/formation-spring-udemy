package com.udemy.demo.user;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;

	@PostMapping(value="/users")
	public ResponseEntity addUser(@Valid @RequestBody UserInfo user) {
		
		List<UserInfo> users = userRepository.findByEmail(user.getEmail());
		if(!users.isEmpty()) {
			return new ResponseEntity("User already existing", HttpStatus.BAD_REQUEST);
		}
		
		userRepository.save(user);
		
		return new ResponseEntity(user, HttpStatus.CREATED);
		
	}
}
