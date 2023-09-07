package com.example.vehicle.response;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.example.vehicle.entities.VehicleMaster;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleMasterApiResponse {

	private VehicleMaster vehicle;
	private List<VehicleMaster> vehicles;
	private HttpStatus status;
	private String message;
	private boolean error;
}
