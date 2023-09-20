package com.capstone.project.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.capstone.project.dto.ReservationDto;
import com.capstone.project.entity.Reservation;
import com.capstone.project.entity.Scooter;
import com.capstone.project.repository.ReservationRepo;
import com.capstone.project.security.entity.User;

@Service
public class ReservationService {
	
	@Autowired ReservationRepo reservationRepo;
	@Autowired @Qualifier("generatesReservation") ObjectProvider<Reservation> reservationProvider;

	public Reservation createReservation() {
		return reservationProvider.getObject();
	}
	
	public void saveReservation(Reservation reservation) {
		reservationRepo.save(reservation);
		System.out.println("Reservation Saved");
	}
	
	public void updateReservation(Reservation reservation) {
		reservationRepo.save(reservation);
	}
	
	public List<Reservation> listReservation () {
		return (List<Reservation>) reservationRepo.findAll();
	}
	
	public Reservation findReservationId(Long id) {
		return reservationRepo.findById(id).get();
	}
	
	public void deleteReservation(Long id) {
		reservationRepo.deleteById(id);
		System.out.println("Reservation Removed");
	}
	
	public Reservation getByDateAndScooter(LocalDate bookingDay, Scooter scooter) {
		return reservationRepo.findReservationByDateAndScooter(bookingDay, scooter);
	}
	
	public Reservation getByUserAndDate(User user,LocalDate bookingDay) {
		return reservationRepo.findReservationByUserAndDate(user, bookingDay);
	}
	
	
}
