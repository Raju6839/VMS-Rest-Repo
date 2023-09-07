package com.example.vehicle.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.vehicle.dtos.VehicleDriverMappingDto;
import com.example.vehicle.response.VehicleDriverApiResponse;
import com.example.vehicle.response.VehicleDriverPageApiResponse;

@RestController
@RequestMapping("/VehicleDriverMapping")
@CrossOrigin(origins="*")
public interface VehicleDriverMappingController {
	
	@PostMapping("/mapVehicleDriver")
	ResponseEntity<VehicleDriverApiResponse> addVehicleDriverMapping(@RequestBody VehicleDriverMappingDto vehicleDriverMappingDto);
	
	@GetMapping("/findByVehicleNoOrFirstName/{date}")
	ResponseEntity<VehicleDriverPageApiResponse> findByVehicleNoOrFirstName(
			@RequestParam Optional<String> plateNumber,
			@RequestParam Optional<String> firstname, @RequestParam Optional<Integer> page,
			@RequestParam Optional<Integer> size, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date);
	
	@GetMapping("/findReportTransactionByDate/{fromDate}/{toDate}")
	ResponseEntity<VehicleDriverPageApiResponse> findReportTransactionByDate(
			@RequestParam Optional<Integer> page,
			@RequestParam Optional<Integer> size, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate , @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate );
	
	@GetMapping("/getAllPagination")
	ResponseEntity<VehicleDriverPageApiResponse> getAllPaginationWithSort(@RequestParam Optional<Integer> page,
				@RequestParam Optional<Integer> size, @RequestParam String dir);
	
	@DeleteMapping("/{id}")
	ResponseEntity<VehicleDriverApiResponse> deleteById(@PathVariable Integer id);
	
	@PutMapping("")
	ResponseEntity<VehicleDriverApiResponse> updateById(@RequestBody VehicleDriverMappingDto vehicleDriverMappingDto);
	
	@PutMapping("/updateMapVehicleDriver")
	ResponseEntity<VehicleDriverApiResponse> updateByVehicleNumAndLicenseNum(@RequestBody VehicleDriverMappingDto vehicleDriverMappingDto);
	
	@PostMapping("/addMapVehicleDriver")
	ResponseEntity<VehicleDriverApiResponse> addByVehicleNumAndLicenseNum(@RequestBody VehicleDriverMappingDto vehicleDriverMappingDto);

}
