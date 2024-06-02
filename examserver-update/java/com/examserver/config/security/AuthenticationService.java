package com.examserver.config.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.examserver.config.User;
import com.examserver.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	private final UserRepository userRepository;

	private final AuthenticationManager authenticationManager;

	public User authenticate(JwtRequest input) {
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(input.getUsername(), input.getPassword()));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return userRepository.findByUsername(input.getUsername()).orElseThrow();
	}
}