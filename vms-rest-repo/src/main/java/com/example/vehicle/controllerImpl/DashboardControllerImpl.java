package com.example.vehicle.controllerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vehicle.controller.DashboardController;
import com.example.vehicle.service.DashboardService;


@Service
public class DashboardControllerImpl implements DashboardController {
	
	@Autowired
	private DashboardService dashboardService;

	@Override
	public int getCountOfActiveVehicle() {
		return dashboardService.getCountOfActiveVehicle();
	}

	@Override
	public int getCountOfNonActiveVehicle() {
		return dashboardService.getCountOfNonActiveVehicle();
	}

	@Override
	public int getCountOfActiveDriver() {
		return dashboardService.getCountOfActiveDriver();
	}

	@Override
	public int getCountOfNonActiveDriver() {
		return dashboardService.getCountOfNonActiveDriver();
	}

}
