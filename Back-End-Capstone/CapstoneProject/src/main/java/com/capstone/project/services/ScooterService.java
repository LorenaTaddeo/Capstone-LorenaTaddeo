package com.capstone.project.services;

import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.capstone.project.dto.ScooterDto;
import com.capstone.project.entity.Scooter;
import com.capstone.project.repository.ReservationRepo;
import com.capstone.project.repository.ScooterRepo;

@Service
public class ScooterService {
	
	@Autowired ScooterRepo scooterRepo;
	@Autowired ReservationRepo reservationRepo;
	@Autowired @Qualifier("generatesScooter") private ObjectProvider<Scooter> scooterProvider;

	public Scooter createScooter() {
		return scooterProvider.getObject();
	}
	
	public void saveScooter(ScooterDto scooterDto) {
		Scooter s = new Scooter();
		s.setBrand(scooterDto.getBrand());
		s.setColor(scooterDto.getColor());
		s.setModel(scooterDto.getModel());
		s.setPlate(scooterDto.getPlate());
		scooterRepo.save(s);
		System.out.println("Scooter Saved");
	}
	
	public void updateScooter(Scooter scooter) {
		scooterRepo.save(scooter);
	}
	
	public List<Scooter> listScooter () {
		return (List<Scooter>) scooterRepo.findAll();
	}
	
	public Scooter findScooterId(Long id) {
		return scooterRepo.findById(id).get();
	}
	
	public void deleteScooter(Long id) {
		scooterRepo.deleteById(id);
		System.out.println("Scooter Removed");
	}
	
	public Scooter getByPlate(String plate) {
		return scooterRepo.findByPlate(plate).get();
	}
	
	public List<Scooter> getByDate(LocalDate bookingDay) {
		return scooterRepo.findFreeScooter(bookingDay);
	}
	
	}
