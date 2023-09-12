package com.capstone.project.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@ToString
@Data
@Table(name = "reservations")
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "booking_day")
	private LocalDate bookingDay;
	
	@ManyToOne
	private Scooter scooter;
	
	@ManyToOne
	private User user;


	public Reservation(LocalDate bookingDay, Scooter scooter, User user) {
		super();
		this.bookingDay = bookingDay;
		this.scooter = scooter;
		this.user = user;
	}
	
	
	
	

}
