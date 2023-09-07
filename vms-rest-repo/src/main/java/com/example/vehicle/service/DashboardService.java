package com.example.vehicle.service;

import org.springframework.stereotype.Service;

@Service
public interface DashboardService {
	
	public int getCountOfActiveVehicle();
	
	public int getCountOfNonActiveVehicle();
	
	public int getCountOfActiveDriver();
	
	public int getCountOfNonActiveDriver();

}
