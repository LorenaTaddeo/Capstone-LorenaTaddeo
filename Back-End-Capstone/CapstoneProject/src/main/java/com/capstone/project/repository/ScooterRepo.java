package com.capstone.project.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capstone.project.entity.Scooter;

@Repository
public interface ScooterRepo extends CrudRepository<Scooter, Long>{

	Optional<Scooter> findByPlate(String plate);
	
	Boolean existsByPlate(String plate);
}
