package com.example.vehicle.entities;

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

@Data
@Entity
@Table(name = "transaction_report")
@NoArgsConstructor
@AllArgsConstructor
public class TransactionReport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "vehicle_id")
	private VehicleMaster vehicleMaster;
	
	@ManyToOne
	@JoinColumn(name = "driver_id")
	private DriverMaster driverMaster;
	
	@ManyToOne
	@JoinColumn(name = "vehicle_driver_mapping_id")
	private VehicleDriverMapping vehicleDriverMapping;
	
	@Column(name= "date")
	private Date date;
	
	
}
