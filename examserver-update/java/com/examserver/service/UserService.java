package com.examserver.service;

import com.examserver.dto.UserDto;
import com.examserver.exception.UserFoundException;

public interface UserService {

	// creating user
	public UserDto createUser(UserDto user) throws UserFoundException;

	// get user by username
	public UserDto getUser(String username);

	// delete user by id
	public void deleteUser(Long userId);
}
