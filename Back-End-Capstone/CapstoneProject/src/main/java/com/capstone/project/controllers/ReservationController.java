package com.capstone.project.controllers;

import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.project.dto.ReservationDto;
import com.capstone.project.dto.UserDto;
import com.capstone.project.entity.Reservation;
import com.capstone.project.entity.Scooter;
import com.capstone.project.security.entity.User;
import com.capstone.project.services.ReservationService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

	@Autowired ReservationService reservationService;
	
	@GetMapping
	public ResponseEntity<List<Reservation>> getAll(){
		return new ResponseEntity<List<Reservation>>(reservationService.listReservation(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getReservationById(@PathVariable Long id){
		try {
			Reservation r = reservationService.findReservationId(id);
			return new ResponseEntity<Reservation>(r, HttpStatus.OK);	
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/bookingDay-scooter")
	public ResponseEntity<?> getReservationByDateAndScooter(@RequestParam LocalDate bookingDay, @RequestParam Scooter scooter) {
		try {
			Reservation r = reservationService.getByDateAndScooter(bookingDay, scooter);
			return new ResponseEntity<Reservation>(r, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/user-bookingDay")
	public ResponseEntity<?> getReservationByUserAndDate(@RequestParam User user, @RequestParam LocalDate bookingDay) {
		try {
			Reservation r = reservationService.getByUserAndDate(user, bookingDay);
			return new ResponseEntity<Reservation>(r, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<String> newReservation(@RequestBody Reservation reservation){
		reservationService.saveReservation(reservation);
		return new ResponseEntity<String>("New Reservation!", HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Reservation> updateReservation(@RequestBody ReservationDto reservation, @PathVariable Long id){
		Reservation r = reservationService.findReservationId(id);
		r.setBookingDay(reservation.getBookingDay());
		r.setScooter(reservation.getScooter());
		r.setUser(reservation.getUser());
		reservationService.saveReservation(r);
		return new ResponseEntity<Reservation>(r, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeReservationById(@PathVariable Long id){
		try {
			Reservation r = reservationService.findReservationId(id);
			reservationService.deleteReservation(id);
			return new ResponseEntity<Reservation>(r, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
