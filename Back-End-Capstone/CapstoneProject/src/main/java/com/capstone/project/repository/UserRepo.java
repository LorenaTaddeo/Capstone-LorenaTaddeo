package com.capstone.project.repository;

import org.springframework.data.repository.CrudRepository;

import com.capstone.project.security.entity.User;

public interface UserRepo extends CrudRepository<User, Long>{


}
