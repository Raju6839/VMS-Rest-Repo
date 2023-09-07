package com.example.vehicle.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vehicle.entities.UserMaster;
import com.example.vehicle.response.UserMasterApiResponse;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public interface UserMasterController {
	
	@PostMapping("/add")
    ResponseEntity<UserMasterApiResponse> add(@RequestBody UserMaster user);

    @GetMapping("/validate/{username}/{password}")
    ResponseEntity<UserMasterApiResponse> validateUser(@PathVariable String username, @PathVariable String password);

}
