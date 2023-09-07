package com.example.vehicle.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.vehicle.entities.VehicleDriverMapping;

@Repository
public interface VehicleDriverMappingRepository extends MappedTypeRepository<VehicleDriverMapping> {
	
	@Query("from VehicleDriverMapping vdm where (vdm.vehicleMaster.id=?1 and CAST(vdm.date as date)=?2) and vdm.active=false")
	List<VehicleDriverMapping> findVehicleDriverMappingByVehicleId(int vehicleId,Date date);
	
	@Query("from VehicleDriverMapping vdm where (vdm.driverMaster.id=?1 and  CAST(vdm.date as date)=?2) and vdm.active=false")
	List<VehicleDriverMapping> findVehicleDriverMappingByDriverIdAndDate(int driverId,Date date);
	
	@Query("from VehicleDriverMapping vdm where vdm.active=false")
	Page<VehicleDriverMapping> findAllByPaginationWithSort(Pageable pageable);
	
//	@Query("from VehicleDriverMapping vdm where (vdm.driverMaster.id=?1 and  CAST(vdm.date as date)=?2) and vdm.active=false")
//	List<VehicleDriverMapping> findVehicleDriverMappingByDriverIdAndDate(int driverId,Date date);
	
	@Query("from VehicleDriverMapping vdm where (vdm.driverMaster.id=?1 and  CAST(vdm.date as date)=?2) and vdm.active=false and vdm.id!=?3")
	List<VehicleDriverMapping> findVehicleDriverMappingByDriverIdDate(int driverId,Date date,int id);
	
	@Query("from VehicleDriverMapping vdm where (vdm.vehicleMaster.id=?1 and CAST(vdm.date as date)=?2) and vdm.active=false and vdm.id!=?3")
	List<VehicleDriverMapping> findVehicleDriverMappingByVehicleIdDate(int vehicleId,Date date,int id);

	@Query("from VehicleDriverMapping vdm where CAST(vdm.date as date)=?1 and vdm.active=false ORDER BY vdm.date DESC")
	Page<VehicleDriverMapping> findAllPaginationByDate(Date date, Pageable pageable);
	
	@Query("from VehicleDriverMapping vdm where vdm.vehicleMaster.plateNumber like %?1% and CAST(vdm.date as date)=?2 and vdm.active=false ORDER BY vdm.date DESC")
	Page<VehicleDriverMapping> findByDateAndVehicleNum(Optional<String> vehicleNum, Date date,  Pageable pageable);
	
	@Query("from VehicleDriverMapping vdm where vdm.driverMaster.firstname like %?1% and CAST(vdm.date as date)=?2 and vdm.active=false ORDER BY vdm.date DESC")
	Page<VehicleDriverMapping> findByDateAndFirstName(Optional<String> firstname,Date date, Pageable pageable);
	
	@Query("from VehicleDriverMapping vdm where vdm.driverMaster.firstname like %?2% and vdm.vehicleMaster.plateNumber like %?1% and CAST(vdm.date as date)=?3 and vdm.active=false ORDER BY vdm.date DESC")
	Page<VehicleDriverMapping> findByDateAndVehicleNumAndFirstName(Optional<String> vehicleNum, Optional<String> firstname , Date date, Pageable pageable);
	
	@Query("from VehicleDriverMapping vdm where vdm.active=false and CAST(vdm.date as date)=?1 ORDER BY vdm.date DESC")
	Page<VehicleDriverMapping> findPageByDate(Date date,Pageable pageable);
	
	@Query("from VehicleDriverMapping vdm where vdm.active=false and (CAST(vdm.date as date) between ?1 and ?2) ORDER BY vdm.date ASC")
	Page<VehicleDriverMapping> findTransactionByDate(Date fromDate, Date toDate,Pageable pageable);
	
	@Query("from VehicleDriverMapping vdm where vdm.id=?1 and vdm.active = false")
	Optional<VehicleDriverMapping> findById(int id);
	

	
}
