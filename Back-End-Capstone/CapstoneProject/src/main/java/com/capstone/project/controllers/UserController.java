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

import com.capstone.project.security.entity.User;
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
	public void newUser(@RequestBody User user) {
		userService.saveUser(user);
	}
	
	@PutMapping
	public ResponseEntity<User> updateUser(@RequestBody User user){
		userService.saveUser(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<User> removeUserById(@PathVariable Long id){
		User u = userService.findUserId(id);
		userService.deleteUser(id);
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}
	
	

}
