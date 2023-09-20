package com.capstone.project.services;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.capstone.project.dto.UserDto;
import com.capstone.project.repository.UserRepo;
import com.capstone.project.security.entity.User;

@Service
public class UserService {

	@Autowired UserRepo userRepo;
	@Autowired @Qualifier ("generatesUser") ObjectProvider<User> userProvider;
	
	public void saveUser(User user) {
		userRepo.save(user);
		System.out.println("User Saved");
	}
	
	public void updateUser(User user) {
		userRepo.save(user);
	}
	
	public List<User> listUsers () {
		return (List<User>) userRepo.findAll();
	}
	
	public User findUserId(Long id) {
		return userRepo.findById(id).get();
	}
	
	public void deleteUser(Long id) {
		userRepo.deleteById(id);
		System.out.println("User Removed");
	}
	
	
}
