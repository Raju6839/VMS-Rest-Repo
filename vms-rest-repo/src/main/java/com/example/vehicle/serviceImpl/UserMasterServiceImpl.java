package com.example.vehicle.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.vehicle.entities.UserMaster;
import com.example.vehicle.repositories.UserMasterRepository;
import com.example.vehicle.response.UserMasterApiResponse;
import com.example.vehicle.service.UserMasterService;

@Service
public class UserMasterServiceImpl implements UserMasterService {
	
	@Autowired
	private UserMasterRepository userRepository;

	@Override
	public UserMasterApiResponse add(UserMaster user) {
		Optional<UserMaster> userIdFetchedFromDb = userRepository.findByUsername(user.getUsername());
		if (userIdFetchedFromDb.isPresent()) {
			UserMaster user1 = userIdFetchedFromDb.get();
			System.out.println(user1);
				return new UserMasterApiResponse( null,null, HttpStatus.ALREADY_REPORTED,
						"Username already exists", true);
			} else {
			UserMaster userSavedToDb = userRepository.save(user);
		return new UserMasterApiResponse(userSavedToDb ,null, HttpStatus.CREATED, "User added successfully", true);
	}
 }

	@Override
	public UserMasterApiResponse validateUser(String username, String password) {
		
		final Optional<UserMaster> optionalUserByUserId = userRepository.findByUsername(username);
		if (optionalUserByUserId.isPresent()) {
			final UserMaster userByUserId = optionalUserByUserId.get();
			if (userByUserId.getPassword().equals(password)) {
				return new UserMasterApiResponse(userByUserId ,null, HttpStatus.OK,
						"User validated", false);
			} else {
				return new UserMasterApiResponse(null,null, HttpStatus.UNAUTHORIZED, "Invalid Password", true);
			}
		}
		return new UserMasterApiResponse(null,null, HttpStatus.UNAUTHORIZED, "User Doesn't Exists", true);
	}
	
}

	

