package com.capstone.project.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capstone.project.security.entity.User;

public interface UserRepo extends CrudRepository<User, Long>{
	
	Optional<User> findByUsername(String username);
	
	Boolean existsByUsername(String username);


}
