package com.example.vehicle.serviceImpl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.vehicle.dtos.VehicleDriverMappingDto;
import com.example.vehicle.entities.DriverMaster;
import com.example.vehicle.entities.VehicleDriverMapping;
import com.example.vehicle.entities.VehicleMaster;
import com.example.vehicle.mappers.VehicleDriverMappingMapper;
import com.example.vehicle.repositories.DriverMasterRepository;
import com.example.vehicle.repositories.VehicleDriverMappingRepository;
import com.example.vehicle.repositories.VehicleMasterRepository;
import com.example.vehicle.response.VehicleDriverApiResponse;
import com.example.vehicle.response.VehicleDriverPageApiResponse;
import com.example.vehicle.service.VehicleDriverMappingService;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class VehicleDriverMappingServiceImpl implements VehicleDriverMappingService {
	
	@Autowired
	private VehicleMasterRepository vehicleMasterRepository;

	@Autowired
	private DriverMasterRepository driverMasterRepository;

	@Autowired
	private VehicleDriverMappingRepository vehicleDriverMappingRepository;
	
	@Autowired
	private VehicleDriverMappingMapper vehicleDriverMappingMapper;
	
	
	@Override
	public VehicleDriverApiResponse addVehicleDriverMapping(VehicleDriverMappingDto vehicleDriverMappingDto) {
		try {
			final String vehicleRegistrationNumberFromUi = vehicleDriverMappingDto.getVehicleMaster()
					.getPlateNumber();
			final Optional<VehicleMaster> vehicleMasterFetchedFromDb = vehicleMasterRepository
					.findByPlateNumber(vehicleRegistrationNumberFromUi);
			if (vehicleMasterFetchedFromDb.isPresent()) {
				final List<VehicleDriverMapping> vehicleDriverMappingFromDb = vehicleDriverMappingRepository
						.findVehicleDriverMappingByVehicleId(vehicleMasterFetchedFromDb.get().getId(),
								vehicleDriverMappingDto.getDate());
				if (vehicleDriverMappingFromDb == null || vehicleDriverMappingFromDb.isEmpty()) {
					final String driverRegistrationNumberFromUi = vehicleDriverMappingDto.getDriverMaster()
							.getLicenseNumber();
					final Optional<DriverMaster> driverMasterFetchedFromDb = driverMasterRepository
							.findByLicenseNumber(driverRegistrationNumberFromUi);
					if (driverMasterFetchedFromDb.isPresent()) {
						final List<VehicleDriverMapping> vehicleDriverMappingFetchedFromDb = vehicleDriverMappingRepository
								.findVehicleDriverMappingByDriverIdAndDate(driverMasterFetchedFromDb.get().getId(),
										vehicleDriverMappingDto.getDate());
						if (vehicleDriverMappingFetchedFromDb == null || vehicleDriverMappingFetchedFromDb.isEmpty()) {
							VehicleDriverMapping vehicleDriverMapping = populateVehicleDriverMappingFromDto(
									vehicleDriverMappingDto);
							vehicleDriverMapping.setActive(false);
							vehicleDriverMapping.setCreationTime(LocalDateTime.now());
							vehicleDriverMappingRepository.save(vehicleDriverMapping);
							return new VehicleDriverApiResponse(
									vehicleDriverMappingMapper
											.mapVehicleDriverMappingToVehicleDriverMappingDto(vehicleDriverMapping),
									null, HttpStatus.OK, "Vehicle Mapped with Driver sucessfully", false);
						} else {
							return new VehicleDriverApiResponse(null, null, HttpStatus.ALREADY_REPORTED,
									"This Driver license number is already been assigned", true);
						}
					} else {
						return new VehicleDriverApiResponse(null, null, HttpStatus.NOT_FOUND,
								"Driver license number not found", true);
					}
				} else {
					return new VehicleDriverApiResponse(null, null, HttpStatus.ALREADY_REPORTED,
							"This Vehicle number is already been assigned", true);
				}
			} else {
				return new VehicleDriverApiResponse(null, null, HttpStatus.NOT_FOUND, "Vehicle Number not found", true);
			}
			} catch (final org.hibernate.exception.ConstraintViolationException
					| org.springframework.dao.DataIntegrityViolationException e) {
//				log.error("ConstraintViolationException ", e);
				if (e.getCause().getCause().getMessage().contains("IX_truck_driver_mapping")) {
					return new VehicleDriverApiResponse(null, null, HttpStatus.BAD_REQUEST,
							"PO number " +vehicleDriverMappingDto+" already exists",  true);
				}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
//			log.error("database connectivity error", e);
		}
		return new VehicleDriverApiResponse(null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);
	}
	
	@Override
	public VehicleDriverPageApiResponse findByVehicleNoOrFirstNamePagination(Optional<String> plateNumber, 
			Optional<String> firstname, Optional<Integer> page, Optional<Integer> size,
			Date date) {
		log.info("findByVehicleNoOrLicenseNumberPagination called");
		try {
			if ((!plateNumber.isPresent() && !firstname.isPresent())) {
				final Page<VehicleDriverMapping> vehicleDriverMappingList = vehicleDriverMappingRepository
						.findAllPaginationByDate(date,
								PageRequest.of(page.orElse(0), size.orElse(5)));
				return commonVehicleDriverMappingsDetails(vehicleDriverMappingList);
				
			} else if (plateNumber.isPresent() && !firstname.isPresent()) {
				final Page<VehicleDriverMapping> vehicleDriverMappingList = vehicleDriverMappingRepository
						.findByDateAndVehicleNum(plateNumber, date,
								PageRequest.of(page.orElse(0), size.orElse(5)));
				return commonVehicleDriverMappingsDetails(vehicleDriverMappingList);
				
			} else if (firstname.isPresent() && !plateNumber.isPresent()) {
				final Page<VehicleDriverMapping> vehicleDriverMappingList = vehicleDriverMappingRepository
						.findByDateAndFirstName(firstname, date,
								PageRequest.of(page.orElse(0), size.orElse(5)));
				return commonVehicleDriverMappingsDetails(vehicleDriverMappingList);
				
			} else if (firstname.isPresent() && plateNumber.isPresent()){
				final Page<VehicleDriverMapping> vehicleDriverMappingList = vehicleDriverMappingRepository
						.findByDateAndVehicleNumAndFirstName(plateNumber, firstname, date,
								PageRequest.of(page.orElse(0), size.orElse(5)));
				return commonVehicleDriverMappingsDetails(vehicleDriverMappingList);
				
			}else {
				final Page<VehicleDriverMapping> VehicleNoOrLicenseNumberList = vehicleDriverMappingRepository
						.findPageByDate(date,PageRequest.of(page.orElse(0), size.orElse(5)));
				Page<VehicleDriverMappingDto> vehicleDriverMappingDto = vehicleDriverMappingMapper
						.mapVehicleDriverMappingToVehicleDriverMappingDto(VehicleNoOrLicenseNumberList);
				
			log.info("findByVehicleNoOrLicenseNumberPagination ended");	
				return new VehicleDriverPageApiResponse(vehicleDriverMappingDto, HttpStatus.FOUND,
						"VehicleDriverMapping list found", false);
				
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("TruckDriverMapping findByTruckNoOrLicenseNumberPagination JDBCConnectionException:", e);
		} catch (final Exception e) {
			log.error("TruckDriverMapping findByTruckNoOrLicenseNumberPagination Exception: ", e);
		}
		
		return new VehicleDriverPageApiResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);
	}
	
	
	
	
//	public VehicleDriverPageApiResponse findByVehicleNoOrLicenseNumberPagination(Optional<Integer> transporterId,
//			Optional<String> plateNumber, Optional<String> licenseNumber, Optional<Integer> page, Optional<Integer> size,
//			Date date) {
//		try {
//			if (transporterId.isPresent() && (!plateNumber.isPresent() && !licenseNumber.isPresent())) {
//				final Page<VehicleDriverMapping> vehicleDriverMappingList = vehicleDriverMappingRepository
//						.findAllPaginationByTransporterId(transporterId, date,
//								PageRequest.of(page.orElse(0), size.orElse(3)));
//				return commonVehicleDriverMappingsDetails(vehicleDriverMappingList);
//			} else if (!transporterId.isPresent() && (plateNumber.isPresent() && licenseNumber.isPresent())) {
//				final Page<VehicleDriverMapping> vehicleNoAndLicenseNumberList = vehicleDriverMappingRepository
//						.findPageByTruckNoAndLicenseNumber(plateNumber.orElse("-"), licenseNumber.orElse("-"),
//								PageRequest.of(page.orElse(0), size.orElse(3)), date);
//				return commonVehicleDriverMappingsDetails(vehicleNoAndLicenseNumberList);
//			} else if (!transporterId.isPresent() && (plateNumber.isPresent() || licenseNumber.isPresent())) {
//				final Page<VehicleDriverMapping> vehicleNoOrLicenseNumberList = vehicleDriverMappingRepository
//						.findPageByTruckNoOrLicenseNumber(plateNumber.orElse("-"), licenseNumber.orElse("-"),
//								PageRequest.of(page.orElse(0), size.orElse(3)), date);
//				return commonVehicleDriverMappingsDetails(vehicleNoOrLicenseNumberList);
//			} else if ((transporterId.isPresent() && plateNumber.isPresent()) && !licenseNumber.isPresent()) {
//				final Page<VehicleDriverMapping> vehicleDriverMappingList = vehicleDriverMappingRepository
//						.findByTransporterIdAndVehicleNum(transporterId, plateNumber, date,
//								PageRequest.of(page.orElse(0), size.orElse(3)));
//				return commonVehicleDriverMappingsDetails(vehicleDriverMappingList);
//			} else if ((transporterId.isPresent() && licenseNumber.isPresent()) && !plateNumber.isPresent()) {
//				final Page<VehicleDriverMapping> vehicleDriverMappingList = vehicleDriverMappingRepository
//						.findByTransporterIdAndLicenseNum(transporterId, licenseNumber, date,
//								PageRequest.of(page.orElse(0), size.orElse(3)));
//				return commonVehicleDriverMappingsDetails(vehicleDriverMappingList);
//			} else if((transporterId.isPresent() && licenseNumber.isPresent()) && plateNumber.isPresent() ){
//				final Page<VehicleDriverMapping> vehicleDriverMappingList = vehicleDriverMappingRepository
//						.findByTransporterIdAndVehicleNumAndLicenseNum(transporterId, plateNumber, licenseNumber, date,
//								PageRequest.of(page.orElse(0), size.orElse(3)));
//				return commonVehicleDriverMappingsDetails(vehicleDriverMappingList);
//			}else {
//				final Page<VehicleDriverMapping> VehicleNoOrLicenseNumberList = vehicleDriverMappingRepository
//						.findPageByDate(PageRequest.of(page.orElse(0), size.orElse(3)), date);
//				Page<VehicleDriverMappingDto> vehicleDriverMappingDto = vehicleDriverMappingMapper
//						.mapVehicleDriverMappingToVehicleDriverMappingDto(VehicleNoOrLicenseNumberList);
//				return new VehicleDriverPageApiResponse(vehicleDriverMappingDto, HttpStatus.FOUND,
//						"TruckDriverMapping list found", false);
//			}
//		} catch (final org.hibernate.exception.JDBCConnectionException e) {
////			log.error("TruckDriverMapping findByTruckNoOrLicenseNumberPagination JDBCConnectionException:", e);
//		} catch (final Exception e) {
////			log.error("TruckDriverMapping findByTruckNoOrLicenseNumberPagination Exception: ", e);
//		}
//		;
//		return new VehicleDriverPageApiResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);
//	}
	
	
	public VehicleDriverPageApiResponse commonVehicleDriverMappingsDetails(Page<VehicleDriverMapping> vehicleDriverMappingObj) {
		if (vehicleDriverMappingObj != null && !vehicleDriverMappingObj.isEmpty()) {
			final Page<VehicleDriverMappingDto> vehicleDriverMappingListTobeSend = vehicleDriverMappingMapper
					.mapVehicleDriverMappingToVehicleDriverMappingDto(vehicleDriverMappingObj);
			return new VehicleDriverPageApiResponse(vehicleDriverMappingListTobeSend, HttpStatus.FOUND,
					"VehicleDriverMapping List  found", false);
		} else {
			return new VehicleDriverPageApiResponse(null, HttpStatus.NOT_FOUND, "VehicleDriverMapping List Not found",
					true);
		}
	}


	@Override
	public VehicleDriverPageApiResponse getAllPaginationWithSort(Optional<Integer> page, Optional<Integer> size,String dir ) {
		try {
			Page<VehicleDriverMapping> vehicleDriverMappingList = null;
			if(dir.equals("asc")) {
				vehicleDriverMappingList = vehicleDriverMappingRepository
						.findAllByPaginationWithSort(PageRequest.of(page.orElse(0), size.orElse(5),Sort.by("date").ascending()));
			
			}else if(dir.equals("desc")) {
				vehicleDriverMappingList = vehicleDriverMappingRepository
						.findAllByPaginationWithSort(PageRequest.of(page.orElse(0), size.orElse(5),Sort.by("date").descending()));
			}
			if (null != vehicleDriverMappingList) {
				final Page<VehicleDriverMappingDto> vehicleDriverMappingListDto = vehicleDriverMappingMapper
						.mapVehicleDriverMappingToVehicleDriverMappingDto(vehicleDriverMappingList);
				return new VehicleDriverPageApiResponse(vehicleDriverMappingListDto, HttpStatus.FOUND,
						"VehicleDriverMapping list found", false);
			} else {
				return new VehicleDriverPageApiResponse(null, HttpStatus.NOT_FOUND, "VehicleDriverMapping list Not found",
						false);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("database connectivity error" + e);
			return new VehicleDriverPageApiResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);
		}
	}
	
	@Override
	public VehicleDriverApiResponse deleteById(int id) {
		try {
			Optional<VehicleDriverMapping> vehicleDriverMappingDb = vehicleDriverMappingRepository.findById(id);
			if (vehicleDriverMappingDb.isPresent()) {
				VehicleDriverMapping vehicleDriverMapping = vehicleDriverMappingDb.get();
				vehicleDriverMapping.setActive(true);
				vehicleDriverMappingRepository.save(vehicleDriverMapping);
				VehicleDriverMappingDto vehicleDriverMappingDto = vehicleDriverMappingMapper
						.mapVehicleDriverMappingToVehicleDriverMappingDto(vehicleDriverMapping);
				return new VehicleDriverApiResponse(vehicleDriverMappingDto, null, HttpStatus.OK,
						"VehicleDriverMapping deleted Sucessfully ", true);
			} else {
				return new VehicleDriverApiResponse(null, null, HttpStatus.NOT_FOUND,
						"VehicleDriverMapping Id is Not found", false);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
//			log.error("TruckDriverMapping deleteById JDBCConnectionException:", e);
		} catch (final Exception e) {
//			log.error("TruckDriverMapping deleteById Exception: ", e);
		}
		return new VehicleDriverApiResponse(null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);
	}

	
	@Override
	public VehicleDriverApiResponse updateById(VehicleDriverMappingDto vehicleDriverMappingDto) {
		try {
			if (vehicleDriverMappingRepository.findById(vehicleDriverMappingDto.getId()).isPresent()) {
				final String vehicleRegistrationNumberFromUi = vehicleDriverMappingDto.getVehicleMaster()
						.getPlateNumber();
				final Optional<VehicleMaster> vehicleMasterFetchedFromDb = vehicleMasterRepository
						.findByPlateNumber(vehicleRegistrationNumberFromUi);
				final List<VehicleDriverMapping> vehicleDriverMappingFromDb = (vehicleDriverMappingRepository
						.findVehicleDriverMappingByVehicleIdDate(vehicleMasterFetchedFromDb.get().getId(),
								vehicleDriverMappingDto.getDate(), vehicleDriverMappingDto.getId()));
				if (vehicleMasterFetchedFromDb.isPresent()) {
					if (vehicleDriverMappingFromDb == null || vehicleDriverMappingFromDb.isEmpty()) {
						final String driverRegistrationNumberFromUi = vehicleDriverMappingDto.getDriverMaster()
								.getLicenseNumber();
						final Optional<DriverMaster> driverMasterFetchedFromDb = driverMasterRepository
								.findByLicenseNumber(driverRegistrationNumberFromUi);
						if (driverMasterFetchedFromDb.isPresent()) {
							final List<VehicleDriverMapping> vehicleDriverMappingFetchedFromDb = vehicleDriverMappingRepository
									.findVehicleDriverMappingByDriverIdDate(driverMasterFetchedFromDb.get().getId(),
											vehicleDriverMappingDto.getDate(), vehicleDriverMappingDto.getId());
							if (vehicleDriverMappingFetchedFromDb == null || vehicleDriverMappingFetchedFromDb.isEmpty()) {
								VehicleDriverMapping truckDriverMapping = populateVehicleDriverMappingFromDto(
										vehicleDriverMappingDto);
								truckDriverMapping.setModifiedBy(vehicleDriverMappingDto.getModifiedBy());
								truckDriverMapping.setModifiedTime(LocalDateTime.now());
								final VehicleDriverMapping vehicleDriverMappingUpdated = vehicleDriverMappingRepository
										.save(truckDriverMapping);
								return new VehicleDriverApiResponse(
										vehicleDriverMappingMapper.mapVehicleDriverMappingToVehicleDriverMappingDto(
												vehicleDriverMappingUpdated),
										null, HttpStatus.OK, "VehicleDriverMapping is Updated sucessfully", false);
							} else {
								return new VehicleDriverApiResponse(null, null, HttpStatus.ALREADY_REPORTED,
										"This Driver license number is already been assigned on this date.", true);
							}
						} else {
							return new VehicleDriverApiResponse(null, null, HttpStatus.NOT_FOUND,
									"Driver license number not found", true);
						}
					} else {
						return new VehicleDriverApiResponse(null, null, HttpStatus.ALREADY_REPORTED,
								"This Vehicle number is already been assigned on this date.", true);
					}
				} else {
					return new VehicleDriverApiResponse(null, null, HttpStatus.NOT_FOUND, "Vehicle Number not found", true);
				}
			} else {
				return new VehicleDriverApiResponse(null, null, HttpStatus.NOT_FOUND,
						"VehicleDriverMapping Id Doesn't exits", false);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
//			log.error("TruckDriverMapping updateById JDBCConnectionException:", e);
		} catch (final Exception e) {
//			log.error("TruckDriverMapping updateById Exception: ", e);
		}
		return new VehicleDriverApiResponse(null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);
	}
	
	@Override
	public VehicleDriverApiResponse updateByVehicleNumAndLicenseNum(VehicleDriverMappingDto vehicleDriverMappingDto) {
		try {
			VehicleDriverMapping vehicleDriverMapping = populateVehicleDriverMappingFromDto(vehicleDriverMappingDto);
			vehicleDriverMapping.setModifiedBy(vehicleDriverMappingDto.getModifiedBy());
			vehicleDriverMapping.setModifiedTime(LocalDateTime.now());
			final VehicleDriverMapping vehicleDriverMappingUpdated = vehicleDriverMappingRepository.save(vehicleDriverMapping);
			return new VehicleDriverApiResponse(
					vehicleDriverMappingMapper.mapVehicleDriverMappingToVehicleDriverMappingDto(vehicleDriverMappingUpdated),
					null, HttpStatus.OK, "VehicleDriverMapping is Updated sucessfully", false);
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
//			log.error("TruckDriverMapping updateByTruckNumAndLicenseNum JDBCConnectionException:", e);
		} catch (final Exception e) {
//			log.error("TruckDriverMapping updateByTruckNumAndLicenseNum Exception: ", e);
		}
		return new VehicleDriverApiResponse(null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);
	}

	@Override
	public VehicleDriverApiResponse addByVehicleNumAndLicenseNum(VehicleDriverMappingDto vehicleDriverMappingDto) {
		try {
			VehicleDriverMapping vehicleDriverMapping = populateVehicleDriverMappingFromDto(vehicleDriverMappingDto);
			vehicleDriverMapping.setCreatedBy(vehicleDriverMappingDto.getCreatedBy());
			vehicleDriverMapping.setCreationTime(LocalDateTime.now());
			final VehicleDriverMapping vehicleDriverMappingUpdated = vehicleDriverMappingRepository.save(vehicleDriverMapping);
			return new VehicleDriverApiResponse(
					vehicleDriverMappingMapper.mapVehicleDriverMappingToVehicleDriverMappingDto(vehicleDriverMappingUpdated),
					null, HttpStatus.OK, "VehicleDriverMapping is Added sucessfully", false);
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
//			log.error("TruckDriverMapping addByTruckNumAndLicenseNum JDBCConnectionException:", e);
		} catch (final Exception e) {
//			log.error("TruckDriverMapping addByTruckNumAndLicenseNum Exception: ", e);
		}
		return new VehicleDriverApiResponse(null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);
	}
	
	
	public VehicleDriverMapping populateVehicleDriverMappingFromDto(VehicleDriverMappingDto vehicleDriverMappingDto) {
		try {
			final Optional<VehicleMaster> vehicleMasterFetchedFromDb = vehicleMasterRepository
					.findByPlateNumber(vehicleDriverMappingDto.getVehicleMaster().getPlateNumber());
			final Optional<DriverMaster> driverMasterFetchedFromDb = driverMasterRepository
					.findByLicenseNumber(vehicleDriverMappingDto.getDriverMaster().getLicenseNumber());
			VehicleDriverMapping vehicleDriverMapping = vehicleDriverMappingMapper
					.mapDtoToVehicleDriverMapping(vehicleDriverMappingDto);
			vehicleDriverMapping.setVehicleMaster(vehicleMasterFetchedFromDb.get());
			vehicleDriverMapping.setDriverMaster(driverMasterFetchedFromDb.get());
			return vehicleDriverMapping;
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
//			log.error("TruckDriverMapping populateTruckDriverMappingFromDto JDBCConnectionException:", e);
		} catch (final Exception e) {
//			log.error("TruckDriverMapping populateTruckDriverMappingFromDto Exception: ", e);
		}
		return null;
	}

	@Override
	public VehicleDriverPageApiResponse findReportTransactionByDate(Optional<Integer> page, Optional<Integer> size, Date fromDate, Date toDate) {
		log.info("findReportTransactionByDate called");
		try {
			
				final Page<VehicleDriverMapping> VehicleNoOrLicenseNumberList = vehicleDriverMappingRepository
						.findTransactionByDate(fromDate, toDate,PageRequest.of(page.orElse(0), size.orElse(5)));
				Page<VehicleDriverMappingDto> vehicleDriverMappingDto = vehicleDriverMappingMapper
						.mapVehicleDriverMappingToVehicleDriverMappingDto(VehicleNoOrLicenseNumberList);
				
				log.info("findByVehicleNoOrLicenseNumberPagination ended");	
				return new VehicleDriverPageApiResponse(vehicleDriverMappingDto, HttpStatus.FOUND,
						"VehicleDriverMapping list found", false);
				
			
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("VehicleDriverMapping findReportTransactionByDate JDBCConnectionException:", e);
		} catch (final Exception e) {
			log.error("VehicleDriverMapping findReportTransactionByDate Exception: ", e);
		}
		
		return new VehicleDriverPageApiResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);
	}
	
	

	
	
	
	

}
