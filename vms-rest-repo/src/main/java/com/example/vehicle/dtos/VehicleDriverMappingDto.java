package com.example.vehicle.dtos;

import java.time.LocalDateTime;
import java.util.Date;

import com.example.vehicle.entities.DriverMaster;
import com.example.vehicle.entities.VehicleMaster;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehicleDriverMappingDto {

	private int id;
	private VehicleMaster vehicleMaster;
	private DriverMaster driverMaster;
	private Date date;
	private String createdBy;
	private LocalDateTime creationTime;
	private String modifiedBy;
	private LocalDateTime modifiedTime;
	private Boolean active;

}
