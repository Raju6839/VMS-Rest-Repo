package com.example.vehicle.service;

import org.springframework.stereotype.Service;

import com.example.vehicle.entities.UserMaster;
import com.example.vehicle.response.UserMasterApiResponse;

@Service
public interface UserMasterService {

	UserMasterApiResponse add(UserMaster user);
	
	UserMasterApiResponse validateUser(String username, String password);
}
