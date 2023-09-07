package com.example.vehicle.response;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import com.example.vehicle.dtos.TransactionReportDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionReportPageApiResponse {
	
	private Page<TransactionReportDto> transactionReportDto;
	private HttpStatus status;
    private String message;
    private boolean error;

}
