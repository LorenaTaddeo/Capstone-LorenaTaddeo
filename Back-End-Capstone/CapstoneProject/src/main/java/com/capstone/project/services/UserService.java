package com.capstone.project.services;

import org.springframework.beans.factory.ObjectProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.capstone.project.repository.UserRepo;
import com.capstone.project.security.entity.User;

@Service
public class UserService {

	@Autowired UserRepo userRepo;
	@Autowired @Qualifier ("generateUser") ObjectProvider<User> userProvider;
	
	public User createUser(String name, String lastname, String username, String email ) {
		return userProvider.getObject().builder()
				.name(name)
				.lastname(lastname)
				.username(username)
				.email(email)
				.build();
	}
	
	public void saveUser(User u) {
		userRepo.save(u);
		System.out.println(u);
	}
	
	public User findUserId(Long id) {
		return userRepo.findById(id).get();
	}
	
	
	
}
