package com.example.vehicle.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "driver_master")
@AllArgsConstructor
@NoArgsConstructor
public class DriverMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Driver_id")
	private int id;
	
	@Column(name = "First_name")
	private String firstname;
	
	@Column(name = "Last_name")
	private String lastname;
	
	@Column(name = "Phone_no")
	private String phoneNumber;
	
	@Column(name = "License_no")
	private String licenseNumber;
	
	@Column(name = "is_Deleted")
	private boolean active;
	
	

}
