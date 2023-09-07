package com.example.vehicle.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.vehicle.response.TransactionReportApiResponse;

@RestController
@RequestMapping("/transactionReport")
@CrossOrigin(origins="*")
public interface TransactionReportController {
	
	@GetMapping("/findByVehicleNoOrFirstName/{date}")
	ResponseEntity<TransactionReportApiResponse> findByVehicleNoOrFirstName(
			@RequestParam Optional<String> plateNumber,
			@RequestParam Optional<String> firstname, @RequestParam Optional<Integer> page,
			@RequestParam Optional<Integer> size, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date);
	
}
