package com.example.vehicle.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.vehicle.dtos.VehicleMasterDto;
import com.example.vehicle.entities.VehicleMaster;
import com.example.vehicle.mappers.VehicleMasterMapper;
import com.example.vehicle.repositories.VehicleMasterRepository;
import com.example.vehicle.response.VehicleMasterApiResponse;
import com.example.vehicle.response.VehicleMasterPageApiResponse;
import com.example.vehicle.service.VehicleMasterService;

@Service
public class VehicleMasterServiceImpl implements VehicleMasterService {

	@Autowired
	private VehicleMasterRepository vehicleRepository;
	
	@Autowired
	private VehicleMasterMapper vehicleMapper;

	@Override
	public VehicleMaster add(VehicleMaster vehicle) {
		return vehicleRepository.save(vehicle);
	}

//	@Override
//	public VehicleMasterApiResponse getVehicleDetails() {
//		final List<VehicleMaster> vehicleList = vehicleRepository.findAll();
//		if (null != vehicleList && !vehicleList.isEmpty()) {
//			return new VehicleMasterApiResponse(null, vehicleList, HttpStatus.OK, "Vehicle list found", false);
//		} else {
//			return new VehicleMasterApiResponse(null, null, HttpStatus.NOT_FOUND, "Vehicle list Not found", false);
//		}
//	}
	
	@Override
	public List<VehicleMaster> getVehicleDetails() {
		final List<VehicleMaster> vehicleMasterList = (List<VehicleMaster>) vehicleRepository.findAll();
		return vehicleMasterList;
	}

	@Override
	public VehicleMasterApiResponse getVehicleById(int id) {
		final Optional<VehicleMaster> vehicleFetchedFromDb = vehicleRepository.findById(id);
		if (vehicleFetchedFromDb.isPresent()) {
			VehicleMaster vehicleFromDb = vehicleFetchedFromDb.get();
			System.out.println("Sending userFetchedFromDb response: " + vehicleFromDb);
			return new VehicleMasterApiResponse(vehicleFromDb, null, HttpStatus.OK, "Vehicle Id fetched" + id, false);
		} else {
			return new VehicleMasterApiResponse(null, null, HttpStatus.NOT_FOUND, "Vehicle Id doesn't exists", true);
		}
	}

	@Override
	public VehicleMasterApiResponse updateVehicle(VehicleMaster vehicle) {
		final VehicleMaster vehicleFromDb = vehicleRepository.findByVehicleId(vehicle.getId());
		if (vehicleFromDb != null) {
//			 Vehicle vehicleFromDb = vehicleFromDb.get(0);
//			vehicleFromDb.setId((vehicleFromDb.getId()));
			vehicleFromDb.setPlateNumber((vehicle.getPlateNumber()));
			vehicleFromDb.setVehicleType((vehicle.getVehicleType()));
			vehicleFromDb.setOwnerName((vehicle.getOwnerName()));
			vehicleFromDb.setColor((vehicle.getColor()));
			vehicleFromDb.setModel((vehicle.getModel()));

			VehicleMaster updatedVehicle = vehicleRepository.save(vehicleFromDb);
			return new VehicleMasterApiResponse(updatedVehicle, null, HttpStatus.CREATED, "updated successfully", false);
		} else {
			System.out.println("Vehicle Id Does Not Exists : " + vehicle);
			return new VehicleMasterApiResponse(null, null, HttpStatus.ALREADY_REPORTED, "Vehicle Id Does Not Exists", true);
		}
	}

	@Override
	public VehicleMasterApiResponse deleteVehicleById(int id) {
		Optional<VehicleMaster> vehicleIdFetchedFromDb = vehicleRepository.findById(id);
		if (vehicleIdFetchedFromDb.isPresent()) {
			VehicleMaster vehicleFromDb = vehicleIdFetchedFromDb.get();
			vehicleFromDb.setActive(true);
			System.out.println("Updated Vehicle with id: " + id + ", deleted set to true");
			vehicleRepository.save(vehicleFromDb);
			return new VehicleMasterApiResponse(vehicleFromDb, null, HttpStatus.OK, "Vehicle Id deleted successfully" + id,
					false);
		} else {
			return new VehicleMasterApiResponse(null, null, HttpStatus.NOT_FOUND, "Vehicle Id doesn't exists", true);
		}
	}
	
	
	@Override
	public VehicleMasterPageApiResponse getByPlateNumber(String plateNumber,
			Optional<Integer> page, Optional<Integer> size) {
		try {
			final Page<VehicleMaster> vehicleFetchedFromDb = vehicleRepository.findByVehiclePlateNumbers(
					plateNumber, PageRequest.of(page.orElse(0), size.orElse(3)));
			if (vehicleFetchedFromDb != null) {
				Page<VehicleMasterDto> vehicleDto = vehicleMapper
						.mapVehicleMasterToVehilceMasterDto(vehicleFetchedFromDb);
//				log.debug("Sending vehicleFetchedFromDb response: " + vehicleDto);
				return new VehicleMasterPageApiResponse(vehicleDto, HttpStatus.OK, "Vehicle fetched Successfully",
						false);
			} else {
				return new VehicleMasterPageApiResponse(null, HttpStatus.NOT_FOUND, "Vehicle doesn't exists", true);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
//			log.error("getByVehicleRegistrationNumber JDBCConnectionException:", e);
		} catch (final Exception e) {
//			log.error("getByVehicleRegistrationNumber Exception: ", e);
		}
		return new VehicleMasterPageApiResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);
	}
	
	
	@Override
	public Page<VehicleMaster> getAllPagination(int page,  int size) {
			
			final Page<VehicleMaster> pageResult = vehicleRepository.findAllpagination(PageRequest.of(page, size));
			return pageResult;	
		}

	@Override
	public Page<VehicleMaster> findPageByPlateNumber(String plateNumber, int page, int size) {
		
		final Page<VehicleMaster> pageResult = vehicleRepository.findPageByPlateNumber(plateNumber,(PageRequest.of(page, size)));
		System.out.println(pageResult);
		return pageResult;	
		
	}



}
