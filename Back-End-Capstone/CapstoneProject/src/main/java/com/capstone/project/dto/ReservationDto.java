package com.capstone.project.dto;

import java.time.LocalDate;

import com.capstone.project.entity.Scooter;
import com.capstone.project.entity.User;

import lombok.Data;

@Data
public class ReservationDto {
	
	private LocalDate bookingDay;
	
	private Scooter scooter;
	
	private User user;

}
