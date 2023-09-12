package com.capstone.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.project.dto.UserDto;
import com.capstone.project.entity.User;
import com.capstone.project.services.UserService;

@RestController
@RequestMapping("/api/users")
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
	
	@PostMapping
	public void newUser(@RequestBody UserDto user) {
		userService.saveUser(user);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto user){
		userService.saveUser(user);
		return new ResponseEntity<UserDto>(user, HttpStatus.OK);
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
