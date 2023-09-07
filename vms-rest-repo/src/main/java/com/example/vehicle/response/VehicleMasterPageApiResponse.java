package com.example.vehicle.response;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import com.example.vehicle.dtos.VehicleMasterDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehicleMasterPageApiResponse {
	
	private Page<VehicleMasterDto> vehicleDto;
	private HttpStatus status;
    private String message;
    private boolean error;

}
