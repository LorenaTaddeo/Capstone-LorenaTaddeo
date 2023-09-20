package com.capstone.project.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.capstone.project.security.entity.ERole;
import com.capstone.project.security.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
	Optional<Role> findByRoleName(ERole roleName);

}
