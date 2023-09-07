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

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicle_master")
public class VehicleMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Vehicle_id")
	private int id;
	
	@Column(name = "Plate_No")
	private String plateNumber;
	
	@Column(name = "Owner_Name")
	private String ownerName;
		
	@Column(name = "Vehicle_Type")
	private String vehicleType;
	
	@Column(name = "Color")
	private String color;
	
	@Column(name = "Model")
	private String model;
	
	@Column(name = "is_Deleted")
	private boolean active;
	
}
