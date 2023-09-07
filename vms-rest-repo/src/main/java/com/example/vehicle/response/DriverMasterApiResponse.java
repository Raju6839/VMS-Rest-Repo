package com.example.vehicle.response;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.example.vehicle.entities.DriverMaster;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverMasterApiResponse {

	private DriverMaster driver;
	private List<DriverMaster> drivers;
	private HttpStatus status;
	private String message;
	private boolean error;
	
}
