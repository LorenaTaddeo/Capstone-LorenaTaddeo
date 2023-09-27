package com.capstone.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.project.dto.UserDto;
import com.capstone.project.entity.Scooter;
import com.capstone.project.security.entity.User;
import com.capstone.project.services.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired UserService userService;
	
	
	@GetMapping
	public ResponseEntity<List<User>> getAll(){
		return new ResponseEntity<List<User>>(userService.listUsers(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id){
		return new ResponseEntity<User>(userService.findUserId(id), HttpStatus.OK);
	}
	
	@GetMapping("/username/{username}")
	public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
		try {
			User u = userService.getByUsername(username);
			return new ResponseEntity<User>(u, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@RequestBody UserDto user, @PathVariable Long id){
		User u = userService.findUserId(id);
		u.setName(user.getName());
		u.setLastname(user.getLastname());
		u.setUsername(user.getUsername());
		u.setEmail(user.getEmail());
		u.setPassword(user.getPassword());
		userService.saveUser(u);
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeUserById(@PathVariable Long id){
		try {
			User u = userService.findUserId(id);
			userService.deleteUser(id);
			return new ResponseEntity<User>(u, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
