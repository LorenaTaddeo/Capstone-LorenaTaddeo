package com.capstone.project.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.capstone.project.entity.Reservation;
import com.capstone.project.entity.Scooter;
import com.capstone.project.security.entity.User;

public interface ReservationRepo extends CrudRepository<Reservation, Long>{

	@Query("SELECT p FROM Reservation p WHERE p.bookingDay = :bookingDay AND p.scooter = :scooter")
    public Reservation findReservationByDateAndScooter(LocalDate bookingDay, Scooter scooter);

    @Query("SELECT p FROM Reservation p WHERE p.user = :user AND p.bookingDay = :bookingDay")
    public Reservation findReservationByUserAndDate(User user, LocalDate bookingDay);
}
