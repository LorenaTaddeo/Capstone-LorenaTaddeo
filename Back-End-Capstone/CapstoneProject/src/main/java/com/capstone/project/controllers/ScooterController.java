package com.capstone.project.controllers;

import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.project.dto.ScooterDto;
import com.capstone.project.entity.Reservation;
import com.capstone.project.entity.Scooter;
import com.capstone.project.security.entity.User;
import com.capstone.project.services.ScooterService;

@RestController
@RequestMapping("/api/scooter")
@CrossOrigin(origins = "*")
public class ScooterController {

	@Autowired ScooterService scooterService;
	
	
	@GetMapping
	public ResponseEntity<List<Scooter>> getAll(){
		return new ResponseEntity<List<Scooter>>(scooterService.listScooter(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getScooterById(@PathVariable Long id){
		try {
			Scooter s = scooterService.findScooterId(id);
			return new ResponseEntity<Scooter>(s, HttpStatus.OK);	
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/plate/{plate}")
	public ResponseEntity<?> getScooterByPlate(@PathVariable String plate) {
		try {
			Scooter p = scooterService.getByPlate(plate);
			return new ResponseEntity<Scooter>(p, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/bookingDay")
	public ResponseEntity<?> getFreeScooterByDate(@RequestParam LocalDate bookingDay) {
		try {
			List<Scooter> s = scooterService.getByDate(bookingDay);
			return new ResponseEntity <List<Scooter>>(s, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
		
	
	@PostMapping
	public ResponseEntity<String> newScooter(@RequestBody ScooterDto scooter){
		scooterService.saveScooter(scooter);
		return new ResponseEntity<String>("New scooter!", HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeScooterById(@PathVariable Long id){
		try {
			Scooter s = scooterService.findScooterId(id);
			scooterService.deleteScooter(id);
			return new ResponseEntity<Scooter>(s, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
}
