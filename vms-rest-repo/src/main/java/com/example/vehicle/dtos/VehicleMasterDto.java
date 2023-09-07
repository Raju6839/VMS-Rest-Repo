package com.example.vehicle.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleMasterDto {
	
	private int id;
	private String plateNumber;
	private String ownerName;
	private String vehicleType;
	private String color;
	private String model;
	private boolean active;

}
