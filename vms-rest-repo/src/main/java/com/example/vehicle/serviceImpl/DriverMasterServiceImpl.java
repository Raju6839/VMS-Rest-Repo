package com.example.vehicle.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.vehicle.entities.DriverMaster;
import com.example.vehicle.repositories.DriverMasterRepository;
import com.example.vehicle.response.DriverMasterApiResponse;
import com.example.vehicle.service.DriverMasterService;

@Service
public class DriverMasterServiceImpl implements DriverMasterService {
	
	@Autowired
	private DriverMasterRepository driverRepository;

	@Override
	public DriverMaster add(DriverMaster driver) {
		return driverRepository.save(driver);
	}

	
//	@Override
//	public DriverMasterApiResponse getDriverDetails() {
//			final List<DriverMaster> driverList = driverRepository.findAll();
//			if (null != driverList && !driverList.isEmpty()) {
//				return new DriverMasterApiResponse(null, driverList , HttpStatus.OK, "Driver list found",
//						false);
//			} else {
//				return new DriverMasterApiResponse(null, null, HttpStatus.NOT_FOUND, "Driver list Not found", false);
//			}
//	}
	
	@Override
	public List<DriverMaster> getDriverDetails() {
		final List<DriverMaster> driverMasterList = (List<DriverMaster>) driverRepository.findAll();
		return driverMasterList;
	}
	

	@Override
	public DriverMasterApiResponse getDriverById(int id) {
		final Optional<DriverMaster> driverFetchedFromDb = driverRepository.findById(id);
		if(driverFetchedFromDb.isPresent()) {
			DriverMaster driverFromDb = driverFetchedFromDb.get();
			System.out.println("Sending userFetchedFromDb response: " + driverFromDb);
			return new DriverMasterApiResponse(driverFromDb, null, HttpStatus.OK, "Driver Id fetched" + id,
					false);
		} else {
			return new DriverMasterApiResponse(null, null, HttpStatus.NOT_FOUND, "Driver Id doesn't exists", true);
		}		
	}


	@Override
	public DriverMasterApiResponse updateDriver(DriverMaster driver) {
		final DriverMaster driverFromDb = driverRepository.findByDriverId(driver.getId());
		if(driverFromDb!=null) {
//			driverFromDb.setId((driver.getId()));
			driverFromDb.setFirstname((driver.getFirstname()));
			driverFromDb.setLastname((driver.getLastname()));
			driverFromDb.setPhoneNumber((driver.getPhoneNumber()));
			driverFromDb.setLicenseNumber((driver.getLicenseNumber()));
			
			DriverMaster updatedDriver = driverRepository.save(driverFromDb);
			return new DriverMasterApiResponse(updatedDriver, null, HttpStatus.CREATED,
					"updated successfully", false);
		} else {
			System.out.println("Driver Id Does Not Exists : " + driver);
			return new DriverMasterApiResponse(null, null, HttpStatus.ALREADY_REPORTED, "Driver Id Does Not Exists", true);
		}
	}


	@Override
	public DriverMasterApiResponse deleteDriverById(int id) {
		Optional<DriverMaster> driverIdFetchedFromDb = driverRepository.findById(id);
		if(driverIdFetchedFromDb.isPresent()) {
			DriverMaster driverFromDb = driverIdFetchedFromDb.get();
			driverFromDb.setActive(true);
			System.out.println("Updated Driver with id: " + id + ", deleted set to true");
			driverRepository.save(driverFromDb);
			return new DriverMasterApiResponse(driverFromDb, null, HttpStatus.OK, "Driver Id deleted successfully" + id,
					false);
		} else {
			return new DriverMasterApiResponse(null, null, HttpStatus.NOT_FOUND, "Driver Id doesn't exists", true);
		}
	}
	
	@Override
	public Page<DriverMaster> getAllPagination(int page,  int size) {
		
			final Page<DriverMaster> pageResult = driverRepository.findAllpagination(PageRequest.of(page, size));
			return pageResult;	
	}


	@Override
	public Page<DriverMaster> findPageByFirstName(String firstname, int page, int size) {
		
		final Page<DriverMaster> pageResult = driverRepository.findPageByFirstName(firstname , (PageRequest.of(page, size)));
		return pageResult;	
		
	}

	


}
