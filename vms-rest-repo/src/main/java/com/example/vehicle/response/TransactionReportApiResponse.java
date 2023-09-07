package com.example.vehicle.response;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.example.vehicle.dtos.TransactionReportDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionReportApiResponse {

	private TransactionReportDto transactionReportDto;
	private List<TransactionReportDto>  transactionReportDtos;
	private HttpStatus status;
	private String message;
	private boolean error;
	
}
