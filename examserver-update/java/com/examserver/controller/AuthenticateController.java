package com.examserver.controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examserver.config.User;
import com.examserver.config.security.AuthenticationService;
import com.examserver.config.security.JwtRequest;
import com.examserver.config.security.JwtResponse;
import com.examserver.config.security.JwtService;
import com.examserver.service.impl.UserDetailsServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthenticateController {

	private final AuthenticationService authenticationService;

	private final UserDetailsServiceImpl userDetailsService;

	private final JwtService jwtService;

	// generate token

	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {

		User authenticatedUser = authenticationService.authenticate(jwtRequest);

		String token = this.jwtService.generateToken(authenticatedUser);
		return ResponseEntity.ok(new JwtResponse(token));
	}

	// return the details of current user
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal) {
		return ((User) this.userDetailsService.loadUserByUsername(principal.getName()));
	}

}
