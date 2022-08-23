package com.udemy.demo.user;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.demo.jwt.JwtController;
import com.udemy.demo.jwt.JwtFilter;
import com.udemy.demo.jwt.JwtUtils;

@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	JwtController jwtController;
	
	@Autowired
	JwtUtils jwtUtils;

	@PostMapping(value="/users")
	public ResponseEntity addUser(@Valid @RequestBody UserInfo userInfo) {
		
		UserInfo existingUser = userRepository.findByOneEmail(userInfo.getEmail());
		if(existingUser != null) {
			return new ResponseEntity("User already existing", HttpStatus.BAD_REQUEST);
		}
		
		UserInfo user = saveUser(userInfo);
		Authentication authentication = jwtController.logUser(userInfo.getEmail(), userInfo.getPassword());
		String jwt = jwtUtils.generateToken(authentication);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
		
		return new ResponseEntity(user, HttpStatus.CREATED);
		
	}
	
	private UserInfo saveUser(UserInfo userInfo) {
		UserInfo user = new UserInfo();
		user.setEmail(userInfo.getEmail());
		user.setPassword(new BCryptPasswordEncoder().encode(userInfo.getPassword()));
		user.setLastName(StringUtils.capitalize(userInfo.getLastName()));
		user.setFirstName(StringUtils.capitalize(userInfo.getFirstName()));
		userRepository.save(user);
		return user;
	}
}
