package com.examserver.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.examserver.config.User;
import com.examserver.dto.UserDto;

@Component
public class UserMapper {
	public User toEntity(UserDto userDto) {
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		return user;
	}

	public UserDto toDto(User user) {
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(user, userDto);
		return userDto;
	}
}
