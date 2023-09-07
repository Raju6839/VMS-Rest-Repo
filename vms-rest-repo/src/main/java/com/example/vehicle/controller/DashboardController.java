package com.example.vehicle.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Dashboard")
@CrossOrigin(origins = "*")
public interface DashboardController {
	
	@GetMapping("/CountActiveVehicle")
	public int getCountOfActiveVehicle();
	
	@GetMapping("/CountNonActiveVehicle")
	public int getCountOfNonActiveVehicle();
	
	@GetMapping("/CountActiveDriver")
	public int getCountOfActiveDriver();
	
	@GetMapping("/CountNonActiveDriver")
	public int getCountOfNonActiveDriver();
	
	

}
