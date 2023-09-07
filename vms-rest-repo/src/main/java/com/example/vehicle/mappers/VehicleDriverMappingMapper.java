package com.example.vehicle.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import com.example.vehicle.dtos.VehicleDriverMappingDto;
import com.example.vehicle.entities.VehicleDriverMapping;

@Mapper(componentModel = "spring")
public interface VehicleDriverMappingMapper {
	

	VehicleDriverMappingDto mapVehicleDriverMappingToVehicleDriverMappingDto(VehicleDriverMapping vehiclekDriverMapping);


	VehicleDriverMapping mapDtoToVehicleDriverMapping(VehicleDriverMappingDto vehicleDriverMappingDto);

	List<VehicleDriverMappingDto> mapVehicleDriverMappingListToVehicleDriverMappingDtoList(
			List<VehicleDriverMapping> vehicleDriverMappingList);


	default Page<VehicleDriverMappingDto> mapVehicleDriverMappingToVehicleDriverMappingDto(
		    Page<VehicleDriverMapping> vehicleDriverMappingFetchedFromDb) {
		return vehicleDriverMappingFetchedFromDb.map(this::mapVehicleDriverMappingToVehicleDriverMappingDto);
	    }
	

}
