package com.example.vehicle.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import com.example.vehicle.dtos.TransactionReportDto;
import com.example.vehicle.entities.TransactionReport;

@Mapper(componentModel = "spring")
public interface TransactionReportMapper {
	
	TransactionReportDto mapTransactionReportToTransactionReportDto(TransactionReport transactionReport);


	TransactionReport mapDtoToTransactionReport(TransactionReportDto transactionReportDto);

	List<TransactionReportDto> mapTransactionReportListToTransactionReportDtoList(
			List<TransactionReport> TransactionReportList);


	default Page<TransactionReportDto> mapTransactionReportToTransactionReportDto(
		    Page<TransactionReport> transactionReportFetchedFromDb) {
		return transactionReportFetchedFromDb.map(this::mapTransactionReportToTransactionReportDto);
	}
}
