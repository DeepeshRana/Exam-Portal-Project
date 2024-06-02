package com.examserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examserver.config.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByRoleName(String roleName);
}
