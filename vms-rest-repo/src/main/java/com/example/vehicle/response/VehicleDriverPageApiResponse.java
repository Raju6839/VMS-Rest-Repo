package com.example.vehicle.response;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import com.example.vehicle.dtos.VehicleDriverMappingDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehicleDriverPageApiResponse {
	
	private Page<VehicleDriverMappingDto> vehicleDriverMappingDto;
	private HttpStatus status;
    private String message;
    private boolean error;

}
