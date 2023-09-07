package com.example.vehicle.controllerImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.vehicle.controller.VehicleMasterController;
import com.example.vehicle.entities.VehicleMaster;
import com.example.vehicle.response.VehicleMasterApiResponse;
import com.example.vehicle.response.VehicleMasterPageApiResponse;
import com.example.vehicle.service.VehicleMasterService;

@Service
public class VehicleMasterControllerImpl implements VehicleMasterController {

	@Autowired
	private VehicleMasterService vehicleService;
	
	@Override
	public VehicleMaster add(VehicleMaster vehicle) {
		return vehicleService.add(vehicle);
	}


	@Override
	public ResponseEntity<VehicleMasterApiResponse> getVehicleById(int id) {
		ResponseEntity<VehicleMasterApiResponse> responseEntity = new ResponseEntity<>(
				vehicleService.getVehicleById(id), HttpStatus.OK);
		return responseEntity;
	}

	@Override
	public ResponseEntity<VehicleMasterApiResponse> updateVehicle(VehicleMaster vehicle) {
		ResponseEntity<VehicleMasterApiResponse> responseEntity = new ResponseEntity<>(
				vehicleService.updateVehicle(vehicle), HttpStatus.OK);
		return responseEntity;
	}

	@Override
	public ResponseEntity<VehicleMasterApiResponse> deleteVehicleById(int id) {
		ResponseEntity<VehicleMasterApiResponse> responseEntity = new ResponseEntity<>(
				vehicleService.deleteVehicleById(id), HttpStatus.OK);
		return responseEntity;
	}

	@Override
	public List<VehicleMaster> getVehicleDetails() {
		return vehicleService.getVehicleDetails();
	}
	
	@Override
	public Page<VehicleMaster> getAllPagination(int page, int size) {
		return vehicleService.getAllPagination(page, size);
	}

	@Override
	public Page<VehicleMaster> getPageByPlateNumber(String plateNumber, int page, int size) {
		return vehicleService.findPageByPlateNumber(plateNumber, page, size);
	}


	@Override
	public ResponseEntity<VehicleMasterPageApiResponse> findByPlateNumber(String plateNumber, Optional<Integer> pageNum,
			Optional<Integer> pageSize) {
		return new ResponseEntity<>(vehicleService.getByPlateNumber(plateNumber,pageNum,pageSize), HttpStatus.OK);
	}




}
