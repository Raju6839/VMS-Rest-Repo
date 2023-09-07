package com.example.vehicle.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDto {
	
	private VehicleMasterDto vehicleMaster;
	private DriverMasterDto driverMaster;

}
