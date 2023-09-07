package com.example.vehicle.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
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

import com.example.vehicle.entities.VehicleMaster;
import com.example.vehicle.response.VehicleMasterApiResponse;
import com.example.vehicle.response.VehicleMasterPageApiResponse;

@RestController
@RequestMapping("/vehicle")
@CrossOrigin(origins = "*")
public interface VehicleMasterController {

	@PostMapping("/add")
	public VehicleMaster add(@RequestBody VehicleMaster vehicle);
	
	@GetMapping("")
	public List<VehicleMaster> getVehicleDetails();
	
	@GetMapping("/{id}")
	public ResponseEntity<VehicleMasterApiResponse> getVehicleById(@PathVariable(value = "id") int id);
	
	@PutMapping("/update")
	public ResponseEntity<VehicleMasterApiResponse> updateVehicle(@RequestBody VehicleMaster vehicle);
	
	@DeleteMapping("/{id}")
    public ResponseEntity<VehicleMasterApiResponse> deleteVehicleById(@PathVariable(value = "id") int id);
	
	@GetMapping("/getAllPagination")
	public Page<VehicleMaster> getAllPagination(@RequestParam(name = "page", defaultValue = "0") int page , @RequestParam(name = "size", defaultValue = "3") int  size);
    
	@GetMapping("/getPageByPlateNumber/{plateNumber}")
	public Page<VehicleMaster> getPageByPlateNumber(@PathVariable String plateNumber,@RequestParam(name = "page", defaultValue = "0") int page , @RequestParam(name = "size", defaultValue = "3") int  size);
	
	@GetMapping("/vehiclePlateNumber/{plateNumber}")
	public ResponseEntity<VehicleMasterPageApiResponse> findByPlateNumber(@PathVariable String plateNumber,@RequestParam Optional<Integer> pageNum,@RequestParam Optional<Integer> pageSize);
}
