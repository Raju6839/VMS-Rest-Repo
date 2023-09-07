package com.example.vehicle.repositories;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.vehicle.entities.TransactionReport;
import com.example.vehicle.entities.VehicleDriverMapping;

@Repository
public interface TransactionReportRepository extends JpaRepository<TransactionReport, Integer> {

	@Query("from VehicleDriverMapping vdm where vdm.vehicleMaster.plateNumber like %?1% and CAST(vdm.date as date)=?2 and vdm.active=false ORDER BY vdm.date DESC")
	Page<VehicleDriverMapping> findByDateAndVehicleNum(Optional<String> vehicleNum, Date date, Pageable pageable);
	
	@Query("from VehicleDriverMapping vdm where vdm.driverMaster.firstname like %?1% and CAST(vdm.date as date)=?2 and vdm.active=false ORDER BY vdm.date DESC")
	Page<VehicleDriverMapping> findByDateAndFirstName(Optional<String> firstname,Date date, Pageable pageable);
	
	@Query("from VehicleDriverMapping vdm where vdm.driverMaster.firstname like %?2% and vdm.vehicleMaster.plateNumber like %?1% and CAST(vdm.date as date)=?3 and vdm.active=false ORDER BY vdm.date DESC")
	Page<VehicleDriverMapping> findByDateAndVehicleNumAndFirstName(Optional<String> vehicleNum, Optional<String> firstname,Date date, Pageable pageable);
	
	@Query("from VehicleDriverMapping vdm where vdm.active=false and CAST(vdm.date as date)=?1 ORDER BY vdm.date DESC")
	Page<VehicleDriverMapping> findPageByDate(Pageable pageable,Date date);
	
}
