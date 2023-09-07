package com.example.vehicle.response;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.example.vehicle.entities.UserMaster;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMasterApiResponse {

	private UserMaster user;
	private List<UserMaster> users;
	private HttpStatus status;
	private String message;
	private boolean error;
}




