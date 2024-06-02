package com.examserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examserver.config.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public Optional<User> findByUsername(String username);
}
