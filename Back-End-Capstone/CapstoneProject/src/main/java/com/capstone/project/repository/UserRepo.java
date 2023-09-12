package com.capstone.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capstone.project.entity.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long>{


}
