package com.example.vehicle.response;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.example.vehicle.dtos.VehicleDriverMappingDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDriverApiResponse {
	
	private VehicleDriverMappingDto vehicleDriverMappingDto;
	private List<VehicleDriverMappingDto>  vehicleDriverMappingDtos;
	private HttpStatus status;
	private String message;
	private boolean error;
}
