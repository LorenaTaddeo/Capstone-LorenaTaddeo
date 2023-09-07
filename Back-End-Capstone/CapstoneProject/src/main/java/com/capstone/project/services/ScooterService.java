package com.capstone.project.services;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.capstone.project.classes.Scooter;
import com.capstone.project.repository.ScooterRepo;
import com.capstone.project.repository.UserRepo;
import com.capstone.project.security.entity.User;

@Service
public class ScooterService {
	
	@Autowired ScooterRepo scooterRepo;
	@Autowired UserRepo userRepo;
	@Autowired @Qualifier("generateScooter") private ObjectProvider<Scooter> scooterProvider;

	public Scooter createScooter() {
		return scooterProvider.getObject();
	}
	
	public void saveScooter(Scooter scooter) {
		scooterRepo.save(scooter);
		System.out.println("Scooter saved");
	}
	
	public List<Scooter> listScooter () {
		return (List<Scooter>) scooterRepo.findAll();
	}
	
	public Scooter findScooterId(Long id) {
		return scooterRepo.findById(id).get();
	}
	
	public void deleteScooter(Long id) {
		scooterRepo.deleteById(id);
	}
	
	public void assignUser(Long user_id, Long scooter_id) {
		User u = userRepo.findById(user_id).get();
		Scooter s = scooterRepo.findById(scooter_id).get();
		
		s.setUser(u);
		scooterRepo.save(s);
		
		
	}
}
