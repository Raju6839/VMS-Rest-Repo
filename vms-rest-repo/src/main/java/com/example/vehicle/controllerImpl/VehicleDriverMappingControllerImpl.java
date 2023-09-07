package com.example.vehicle.controllerImpl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.vehicle.controller.VehicleDriverMappingController;
import com.example.vehicle.dtos.VehicleDriverMappingDto;
import com.example.vehicle.response.VehicleDriverApiResponse;
import com.example.vehicle.response.VehicleDriverPageApiResponse;
import com.example.vehicle.service.VehicleDriverMappingService;


@Service
public class VehicleDriverMappingControllerImpl implements VehicleDriverMappingController {
	
	@Autowired
	private VehicleDriverMappingService vehicleDriverMappingService;

	@Override
	public ResponseEntity<VehicleDriverApiResponse> addVehicleDriverMapping(VehicleDriverMappingDto vehicleDriverMappingDto) {
		return new ResponseEntity<>(vehicleDriverMappingService.addVehicleDriverMapping(vehicleDriverMappingDto), HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<VehicleDriverPageApiResponse> getAllPaginationWithSort(Optional<Integer> page, Optional<Integer> size, String dir) {
		return new ResponseEntity<>(vehicleDriverMappingService.getAllPaginationWithSort(page, size, dir), HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<VehicleDriverApiResponse> deleteById(Integer id) {
		return new ResponseEntity<>(vehicleDriverMappingService.deleteById(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<VehicleDriverApiResponse> updateById(VehicleDriverMappingDto vehicleDriverMappingDto) {
		return  new ResponseEntity<>(vehicleDriverMappingService.updateById(vehicleDriverMappingDto), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<VehicleDriverApiResponse> updateByVehicleNumAndLicenseNum(
			VehicleDriverMappingDto vehicleDriverMappingDto) {
		return  new ResponseEntity<>(vehicleDriverMappingService.updateById(vehicleDriverMappingDto), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<VehicleDriverApiResponse> addByVehicleNumAndLicenseNum(
			VehicleDriverMappingDto vehicleDriverMappingDto) {
		return new ResponseEntity<>(vehicleDriverMappingService.addByVehicleNumAndLicenseNum(vehicleDriverMappingDto), HttpStatus.OK);
	}
	
	

	@Override
	public ResponseEntity<VehicleDriverPageApiResponse> findByVehicleNoOrFirstName(Optional<String> plateNumber,
			Optional<String> firstname, Optional<Integer> page, Optional<Integer> size, Date date) {
		return new ResponseEntity<>(vehicleDriverMappingService.findByVehicleNoOrFirstNamePagination(
				plateNumber, firstname, page, size, date), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<VehicleDriverPageApiResponse> findReportTransactionByDate(Optional<Integer> page,
			Optional<Integer> size, Date fromDate, Date toDate) {
		
		return new ResponseEntity<>(vehicleDriverMappingService.findReportTransactionByDate(page, size, fromDate, toDate), HttpStatus.OK);
	}

	
	
	

}
