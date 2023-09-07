package com.example.vehicle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@ComponentScan({"com.example.vehicle", "com.example.vehicle"})
@SpringBootApplication
@EnableSwagger2
public class VehicleManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleManagementSystemApplication.class, args);
	}
		
}
