package com.capstone.project.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capstone.project.entity.Scooter;

public interface ScooterRepo extends CrudRepository<Scooter, Long>{

	Optional<Scooter> findByPlate(String plate);
	
	Boolean existsByPlate(String plate);
	
	
	   
	 @Query(value = "SELECT s.id, s.color, s.plate, s.brand, s.model from scooters s FULL OUTER JOIN (SELECT * FROM reservations WHERE booking_day=:bookingDay ) AS r ON r.scooter_id=s.id WHERE  r.id is null"
	 		,
	            nativeQuery = true)
	 List<Scooter> findFreeScooter(@Param("bookingDay") LocalDate bookingDay);
}
