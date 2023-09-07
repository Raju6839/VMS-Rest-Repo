package com.example.vehicle.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import com.example.vehicle.dtos.VehicleMasterDto;
import com.example.vehicle.entities.VehicleMaster;


@Mapper(componentModel = "spring")
public interface VehicleMasterMapper {
	
	
	VehicleMasterDto mapVehicleMasterToVehilceMasterDto(VehicleMaster vehicles);

	VehicleMaster mapVehicleMasterDtoToVehilceMaster(VehicleMasterDto vehicleDto);
	
	List<VehicleMasterDto> vehicleMasterListToVehicleMasterDtoList(List<VehicleMaster> vehicleList);
	
	default Page<VehicleMasterDto> mapVehicleMasterToVehilceMasterDto(
			    Page<VehicleMaster> vehicleMasterFetchedFromDb) {
			return vehicleMasterFetchedFromDb.map(this::mapVehicleMasterToVehilceMasterDto);
		    }

}
