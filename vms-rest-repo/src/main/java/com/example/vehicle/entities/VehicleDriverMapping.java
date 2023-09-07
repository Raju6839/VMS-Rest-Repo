package com.example.vehicle.entities;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "vehicle_driver_mapping")
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDriverMapping {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "vehicle_id")
	private VehicleMaster vehicleMaster;
	
	@ManyToOne
	@JoinColumn(name = "driver_id")
	private DriverMaster driverMaster;
	
	@Column(name= "date")
	private Date date;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "creation_time")
	private LocalDateTime creationTime;

	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "modified_time")
	private LocalDateTime modifiedTime;
	
	@Column(name = "is_Deleted")
	private Boolean active;

}
