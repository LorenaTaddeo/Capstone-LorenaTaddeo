package com.capstone.project.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@ToString
@Data
@Table(name = "scooters")
public class Scooter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "plate")
	private String plate;
	
	@Column(name = "model")
	private String model;
	
	@Column(name = "color")
	private String color;
	
	@Column(name = "brand")
	private String brand;

	public Scooter(String plate, String model, String color, String brand) {
		super();
		this.plate = plate;
		this.model = model;
		this.color = color;
		this.brand = brand;
	}
	
	
	
}
