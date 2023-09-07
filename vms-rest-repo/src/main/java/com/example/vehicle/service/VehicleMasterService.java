package com.example.vehicle.service;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.vehicle.entities.VehicleMaster;
import com.example.vehicle.response.VehicleMasterApiResponse;
import com.example.vehicle.response.VehicleMasterPageApiResponse;


@Service
public interface VehicleMasterService {
	
	public VehicleMaster add( VehicleMaster vehicle);
	
	public List<VehicleMaster> getVehicleDetails();
	
	public VehicleMasterApiResponse getVehicleById(int id);
	
	public VehicleMasterApiResponse updateVehicle(VehicleMaster vehicle);
	
	public VehicleMasterApiResponse deleteVehicleById(int id);
	
	public Page<VehicleMaster> getAllPagination(int page, int size);

	public Page<VehicleMaster> findPageByPlateNumber(String plateNumber, int page, int size);

	public VehicleMasterPageApiResponse getByPlateNumber(String plateNumber, Optional<Integer> page, Optional<Integer> size);

}
