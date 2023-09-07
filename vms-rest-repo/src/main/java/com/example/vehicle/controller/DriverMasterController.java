package com.example.vehicle.controller;

import java.util.List;

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

import com.example.vehicle.entities.DriverMaster;
import com.example.vehicle.response.DriverMasterApiResponse;

@RestController
@RequestMapping("/driver")
@CrossOrigin(origins="*")
public interface DriverMasterController {
	
	
	@PostMapping("/add")
	public DriverMaster add(@RequestBody DriverMaster driver);
		
	@GetMapping("")
	public List<DriverMaster> getDriverDetails();
	
	@GetMapping("/{id}")
	public ResponseEntity<DriverMasterApiResponse> getDriverById(@PathVariable(value = "id") int id);
	
	@PutMapping("/update")
	public ResponseEntity<DriverMasterApiResponse> updateDriver(@RequestBody DriverMaster driver );
	
	@DeleteMapping("/{id}")
    public ResponseEntity<DriverMasterApiResponse> deleteDriverById(@PathVariable(value = "id") int id);
	
	@GetMapping("/getAllPagination")
	public Page<DriverMaster> getAllPagination(@RequestParam(name = "page", defaultValue = "0") int page , @RequestParam(name = "size", defaultValue = "3") int  size);
	    
	@GetMapping("/getPageByFirstName/{firstname}")
	public Page<DriverMaster> getPageByFirstName(@PathVariable String firstname , @RequestParam(name = "page", defaultValue = "0") int page , @RequestParam(name = "size", defaultValue = "3") int  size);
}
