package com.example.vehicle.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.vehicle.entities.DriverMaster;
import com.example.vehicle.response.DriverMasterApiResponse;


@Service
public interface DriverMasterService {
	
	public DriverMaster add(DriverMaster driver);
	
	public List<DriverMaster> getDriverDetails();
	
	public DriverMasterApiResponse getDriverById(int id);
	
	public DriverMasterApiResponse updateDriver(DriverMaster driver);
	
	public DriverMasterApiResponse deleteDriverById(int id);
	
	public Page<DriverMaster> getAllPagination(int page, int size);
	
	public Page<DriverMaster> findPageByFirstName(String firstname, int page, int size); 
	
}
