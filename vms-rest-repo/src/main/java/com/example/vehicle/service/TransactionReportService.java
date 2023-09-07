package com.example.vehicle.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.vehicle.response.TransactionReportApiResponse;


@Service
public interface TransactionReportService {
	
	public TransactionReportApiResponse findByVehicleNoOrFirstNamePagination(Optional<String> plateNumber,
			Optional<String> firstname, Optional<Integer> page, Optional<Integer> size, Date date);

	
}
