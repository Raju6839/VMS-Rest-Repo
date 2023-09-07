package com.example.vehicle.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverMasterDto {

	private int id;
	private String firstname;
	private String lastname;
	private String phoneNumber;
	private String licenseNumber;
	private boolean active;
	
}

