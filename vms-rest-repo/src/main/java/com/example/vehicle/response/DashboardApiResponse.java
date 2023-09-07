package com.example.vehicle.response;

import org.springframework.http.HttpStatus;

import com.example.vehicle.dtos.DashboardDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DashboardApiResponse {
	
	private DashboardDto dashboardDto;
	private HttpStatus status;
	private String message;
	private boolean error;

}
