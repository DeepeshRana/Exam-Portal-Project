package com.examserver.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.examserver.config.Role;
import com.examserver.config.User;
import com.examserver.config.UserRole;
import com.examserver.dto.UserDto;
import com.examserver.exception.UserFoundException;
import com.examserver.mapper.UserMapper;
import com.examserver.repository.RoleRepository;
import com.examserver.repository.UserRepository;
import com.examserver.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	private final UserRepository userRepository;

	private final RoleRepository roleRepository;

	private final UserMapper userMapper;

	// creating user
	@Override
	public UserDto createUser(UserDto userDto) throws UserFoundException {
		userDto.setProfile("default.png");

		userDto.setPassword(this.bCryptPasswordEncoder.encode(userDto.getPassword()));

		User user = userMapper.toEntity(userDto);

		Role role = roleRepository.findByRoleName("NORMAL").get();

		Set<UserRole> roles = new HashSet<>();
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);

		roles.add(userRole);

		Optional<User> byUsername = this.userRepository.findByUsername(user.getUsername());
		User save;
		try {
			if (byUsername.isPresent()) {
				System.out.println("User is already there !!");
				throw new UserFoundException("User with this Username is already there in DB !! try with another one");
			} else {
				// user create
				user.getUserRoles().addAll(roles);
				save = this.userRepository.save(user);
			}
			return userMapper.toDto(save);
		}
		catch(Exception e) {
			System.out.println(e);	
		}
		return null; 
		
	}

	// getting user by username
	@Override
	public UserDto getUser(String username) {
		Optional<User> byUsername = this.userRepository.findByUsername(username);
		return userMapper.toDto(byUsername.get());
	}

	@Override
	public void deleteUser(Long userId) {
		this.userRepository.deleteById(userId);
	}

}
