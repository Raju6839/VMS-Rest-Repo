package com.example.vehicle.dtos;

import java.util.Date;

import com.example.vehicle.entities.VehicleDriverMapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionReportDto {
	
	private int id;
	private VehicleDriverMapping vehicleDriverMapping;
	private Date date;

}
