package com.examserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examserver.dto.UserDto;
import com.examserver.exception.UserFoundException;
import com.examserver.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController{

	@Autowired
	private UserService userService;

	// creating user
	@PostMapping("/")
	public UserDto createUser(@RequestBody UserDto user) throws Exception {
		UserDto response = this.userService.createUser(user);
		return response;
	}

	@GetMapping("/{username}")
	public UserDto getUser(@PathVariable("username") String username) {
		return this.userService.getUser(username);
	}

	// delete the user by id
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") Long userId) {
		this.userService.deleteUser(userId);
	}

	// update api
	@ExceptionHandler(UserFoundException.class)
	public ResponseEntity<?> exceptionHandler(UserFoundException ex) {
		return ResponseEntity.ok(ex.getMessage());
	}

}
