package com.example.vehicle.controllerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.vehicle.controller.UserMasterController;
import com.example.vehicle.entities.UserMaster;
import com.example.vehicle.response.UserMasterApiResponse;
import com.example.vehicle.service.UserMasterService;

@Service
public class UserMasterControllerImpl implements UserMasterController {
	
	@Autowired
	private UserMasterService userService;

	@Override
	public ResponseEntity<UserMasterApiResponse> add(UserMaster user) {
		ResponseEntity<UserMasterApiResponse> responseEntity = new ResponseEntity<>(userService.add(user), HttpStatus.OK);
		return responseEntity;
	}

	@Override
	public ResponseEntity<UserMasterApiResponse> validateUser(String username, String password) {
		ResponseEntity<UserMasterApiResponse> responseEntity = new ResponseEntity<>(
				userService.validateUser(username, password), HttpStatus.OK);
		return responseEntity;
	}

}
