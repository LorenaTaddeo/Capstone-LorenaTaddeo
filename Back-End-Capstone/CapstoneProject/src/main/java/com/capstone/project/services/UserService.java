package com.capstone.project.services;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.capstone.project.dto.UserDto;
import com.capstone.project.entity.User;
import com.capstone.project.repository.UserRepo;

@Service
public class UserService {

	@Autowired UserRepo userRepo;
	@Autowired @Qualifier ("generatesUser") ObjectProvider<User> userProvider;
	
	public User createUser(String name, String lastname, String username, String email, String password) {
		return userProvider.getObject().builder()
				.name(name)
				.lastname(lastname)
				.username(username)
				.email(email)
				.password(password)
				.build();
	}
	
	public void saveUser(UserDto userDto) {
		User u = new User();
		u.setName(userDto.getName());
		u.setLastname(userDto.getLastname());
		u.setUsername(userDto.getUsername());
		u.setEmail(userDto.getEmail());
		u.setPassword(userDto.getPassword());
		userRepo.save(u);
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
