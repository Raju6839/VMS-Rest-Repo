package com.example.vehicle.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vehicle.repositories.DashboardRepository;
import com.example.vehicle.service.DashboardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	private DashboardRepository dashboardRepository;

	public int getCountOfActiveVehicle() {
		log.info("<<start>>getCountOfActiveVehicle<<start>>");
		int noOfVehicleActive = dashboardRepository.getCountOfActiveVehicle();
		System.out.println("noOfVehicleActive :" + noOfVehicleActive);
		log.info("<<end>>getCountOfActiveVehicle<<end>>");
		return noOfVehicleActive;
	}
	
	public int getCountOfNonActiveVehicle() {
		log.info("<<start>>getCountOfNonActiveVehicle<<start>>");
		int noOfVehicleNonActive = dashboardRepository.getCountOfNonActiveVehicle();
		System.out.println("noOfVehicleNonActive :" + noOfVehicleNonActive);
		log.info("<<end>>getCountOfActiveVehicle<<end>>");
		return noOfVehicleNonActive;
	}
	
	public int getCountOfActiveDriver() {
		log.info("<<start>>getCountOfActiveDriver<<start>>");
		int noOfActiveDriver = dashboardRepository.getCountOfActiveDriver();
		System.out.println("noOfActiveDriver :" + noOfActiveDriver);
		log.info("<<end>>getCountOfActiveDriver<<end>>");
		return noOfActiveDriver;
	}
	
	public int getCountOfNonActiveDriver() {
		log.info("<<start>>getCountOfNonActiveDriver<<start>>");
		int noOfNonActiveDriver = dashboardRepository.getCountOfNonActiveDriver();
		System.out.println("noOfNonActiveDriver :" + noOfNonActiveDriver);
		log.info("<<end>>getCountOfNonActiveDriver<<end>>");
		return noOfNonActiveDriver;
	}
	
	

}
