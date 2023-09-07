package com.example.vehicle.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.vehicle.dtos.VehicleDriverMappingDto;
import com.example.vehicle.response.VehicleDriverApiResponse;
import com.example.vehicle.response.VehicleDriverPageApiResponse;

@Service
public interface VehicleDriverMappingService {
	
	public VehicleDriverApiResponse addVehicleDriverMapping(VehicleDriverMappingDto vehicleDriverMappingDto);
	
//	public VehicleDriverMapping populateVehicleDriverMappingFromDto(VehicleDriverMappingDto vehicleDriverMappingDto);

	public VehicleDriverPageApiResponse getAllPaginationWithSort(Optional<Integer> page, Optional<Integer> size, String dir);

	public VehicleDriverApiResponse deleteById(int id);
	
	public VehicleDriverApiResponse updateById(VehicleDriverMappingDto vehicleDriverMappingDto);
	
	public VehicleDriverApiResponse updateByVehicleNumAndLicenseNum(VehicleDriverMappingDto vehicleDriverMappingDto);
	
	public VehicleDriverApiResponse addByVehicleNumAndLicenseNum(VehicleDriverMappingDto vehicleDriverMappingDto);

	public VehicleDriverPageApiResponse findByVehicleNoOrFirstNamePagination(Optional<String> plateNumber,
			Optional<String> firstname, Optional<Integer> page, Optional<Integer> size, Date date);

	public VehicleDriverPageApiResponse findReportTransactionByDate(Optional<Integer> page, Optional<Integer> size,
			Date fromDate, Date toDate);
	
//	public VehicleDriverPageApiResponse findByDate(Optional<Integer> page, Optional<Integer> size,
//	Date date);
}
