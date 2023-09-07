package com.capstone.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.project.classes.Scooter;
import com.capstone.project.services.ScooterService;

@RestController
@RequestMapping("/api/scooter")
public class ScooterController {

	@Autowired ScooterService scooterService;
	
	@GetMapping
	public ResponseEntity<List<Scooter>> getAll(){
		return new ResponseEntity<List<Scooter>>(scooterService.listScooter(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Scooter> getScooterById(@PathVariable Long id){
		return new ResponseEntity<Scooter>(scooterService.findScooterId(id), HttpStatus.OK);
	}
	
	@PostMapping("/add/scooter")
	public ResponseEntity<String> newScooter(){
		scooterService.saveScooter(scooterService.createScooter());
		return new ResponseEntity<String>("New scooter!", HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Scooter> removeScooterById(@PathVariable Long id){
		Scooter s = scooterService.findScooterId(id);
		scooterService.deleteScooter(id);
		return new ResponseEntity<Scooter>(s, HttpStatus.OK);
	}
	
}
