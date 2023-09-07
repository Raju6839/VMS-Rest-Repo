package com.example.vehicle.controllerImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.vehicle.controller.DriverMasterController;
import com.example.vehicle.entities.DriverMaster;
import com.example.vehicle.response.DriverMasterApiResponse;
import com.example.vehicle.service.DriverMasterService;

@Service
public class DriverMasterControllerImpl implements DriverMasterController {

	@Autowired
	private DriverMasterService driverService;
	
	@Override
	public DriverMaster add(DriverMaster driver) {
		return driverService.add(driver);
	}

//	@Override
//	public ResponseEntity<DriverMasterApiResponse> getDriverDetails() {
//		ResponseEntity<DriverMasterApiResponse> responseEntity = new ResponseEntity<>(
//				driverService.getDriverDetails(), HttpStatus.OK);
//		return responseEntity;
//	}

	@Override
	public ResponseEntity<DriverMasterApiResponse> getDriverById(int id) {
		ResponseEntity<DriverMasterApiResponse> responseEntity = new ResponseEntity<>(
				driverService.getDriverById(id), HttpStatus.OK);
		return responseEntity;
	}

	@Override
	public ResponseEntity<DriverMasterApiResponse> updateDriver(DriverMaster driver) {
		ResponseEntity<DriverMasterApiResponse> responseEntity = new ResponseEntity<>(
				driverService.updateDriver(driver), HttpStatus.OK);
		return responseEntity;
	}

	@Override
	public ResponseEntity<DriverMasterApiResponse> deleteDriverById(int id) {
		ResponseEntity<DriverMasterApiResponse> responseEntity = new ResponseEntity<>(
				driverService.deleteDriverById(id), HttpStatus.OK);
		return responseEntity;
	}

	@Override
	public List<DriverMaster> getDriverDetails() {
		return driverService.getDriverDetails();
	}

	@Override
	public Page<DriverMaster> getAllPagination(int page,
			int size) {
		return driverService.getAllPagination(page, size);
	}

	@Override
	public Page<DriverMaster> getPageByFirstName(String firstname, int page, int size) {
		
		return driverService.findPageByFirstName(firstname, page, size);
	}

	

//	@Override
//	public Page<DriverMaster> getPageById(int id, int page,
//			int size) {
//		ResponseEntity<DriverMasterApiResponse> responseEntity = new ResponseEntity<>(driverService.findPageByFirstName(firstname,pageNum,pageSize), HttpStatus.OK);
//		return responseEntity;
//	}
	
	

}
