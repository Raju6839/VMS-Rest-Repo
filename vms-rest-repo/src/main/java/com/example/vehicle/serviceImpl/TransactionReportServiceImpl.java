package com.example.vehicle.serviceImpl;

import java.util.Date;
import java.util.Optional;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//
//import com.example.vehicle.dtos.TransactionReportDto;
//import com.example.vehicle.dtos.VehicleDriverMappingDto;
//import com.example.vehicle.entities.TransactionReport;
//import com.example.vehicle.entities.VehicleDriverMapping;
//import com.example.vehicle.mappers.TransactionReportMapper;
//import com.example.vehicle.repositories.DriverMasterRepository;
//import com.example.vehicle.repositories.TransactionReportRepository;
//import com.example.vehicle.repositories.VehicleMasterRepository;
//import com.example.vehicle.response.TransactionReportApiResponse;
//import com.example.vehicle.service.TransactionReportService;

import lombok.extern.slf4j.Slf4j;

//@Service
//@Slf4j
//public class TransactionReportServiceImpl implements TransactionReportService {
	
//	@Autowired
//	private TransactionReportRepository transactionReportRepository;
	
//	@Autowired
//	private VehicleMasterRepository vehicleMasterRepository;

//	@Autowired
//	private DriverMasterRepository driverMasterRepository;

//	@Autowired
//	private TransactionReportMapper transactionReportMapper;
	
	
//	@Override
//	public TransactionReportApiResponse findByVehicleNoOrFirstNamePagination(Optional<String> plateNumber, 
//			Optional<String> firstname, Optional<Integer> page, Optional<Integer> size,
//			Date date) {
//		log.info("findByVehicleNoOrLicenseNumberPagination called");
//		try {
//			if ((!plateNumber.isPresent() && !firstname.isPresent())) {
//				final Page<TransactionReport> transactionReportList = transactionReportRepository
//						.findAllPaginationByDate(date,
//								PageRequest.of(page.orElse(0), size.orElse(5)));
//				return commonVehicleDriverMappingsDetails(transactionReportList);
//				
//			} else if (plateNumber.isPresent() && !firstname.isPresent()) {
//				final Page<TransactionReport> transactionReportList = transactionReportRepository
//						.findByDateAndVehicleNum(plateNumber, date,
//								PageRequest.of(page.orElse(0), size.orElse(5)));
//				return commonVehicleDriverMappingsDetails(transactionReportList);
//				
//			} else if (firstname.isPresent() && !plateNumber.isPresent()) {
//				final Page<TransactionReport> transactionReportList = transactionReportRepository
//						.findByDateAndFirstName(firstname, date,
//								PageRequest.of(page.orElse(0), size.orElse(5)));
//				return commonVehicleDriverMappingsDetails(transactionReportList);
//				
//			} else if (firstname.isPresent() && plateNumber.isPresent()){
//				final Page<TransactionReport> transactionReportList = transactionReportRepository
//						.findByDateAndVehicleNumAndFirstName(plateNumber, firstname, date,
//								PageRequest.of(page.orElse(0), size.orElse(5)));
//				return commonVehicleDriverMappingsDetails(transactionReportList);
//				
//			}else {
//				final Page<TransactionReport> VehicleNoOrLicenseNumberList = transactionReportRepository
//						.findPageByDate(PageRequest.of(page.orElse(0), size.orElse(5)), date);
//				Page<TransactionReportDto> transactionReportDto = transactionReportMapper
//						.mapTransactionReportToTransactionReportDto(VehicleNoOrLicenseNumberList);
//				
//			log.info("findByVehicleNoOrLicenseNumberPagination ended");	
//				return new TransactionReportApiResponse(transactionReportDto, HttpStatus.FOUND,
//						"VehicleDriverMapping list found", false);
//				
//			}
//		} catch (final org.hibernate.exception.JDBCConnectionException e) {
//			log.error("TruckDriverMapping findByTruckNoOrLicenseNumberPagination JDBCConnectionException:", e);
//		} catch (final Exception e) {
//			log.error("TruckDriverMapping findByTruckNoOrLicenseNumberPagination Exception: ", e);
//		}
//		
//		return new TransactionReportApiResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);
//	}
//}
	
	
