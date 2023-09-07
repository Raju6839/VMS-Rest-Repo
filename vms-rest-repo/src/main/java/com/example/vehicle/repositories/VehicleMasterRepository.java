package com.example.vehicle.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.vehicle.entities.VehicleMaster;


@Repository
public interface VehicleMasterRepository extends JpaRepository<VehicleMaster, Integer> {
	
//	@Query(nativeQuery = true, value = "select * from vehicle_master where id=?1")
	@Query("select v from VehicleMaster v where v.id=?1 and v.active = false")
    VehicleMaster findByVehicleId(int id);
	
	@Query(nativeQuery = true, value = "select * from vehicle_master where is_Deleted = 'false'")
	List<VehicleMaster> findAll();
	
	@Query("select v from VehicleMaster v where v.id=?1 and v.active = false")
	public Optional<VehicleMaster> findById(int id);
	
	@Query("select v from VehicleMaster v where v.active = false ORDER BY v.id ASC")
	Page<VehicleMaster> findAllpagination(Pageable page);
	
	@Query("select v from VehicleMaster v where v.plateNumber Like %?1% and v.active=false ORDER BY v.id ASC")
    Page<VehicleMaster> findPageByPlateNumber(String plateNumber,Pageable page);
	
	@Query("select v from VehicleMaster v where v.plateNumber=?1 and v.active=false")
	Optional<VehicleMaster> findByPlateNumber(String plateNumber);
	
	@Query("select v from VehicleMaster v where v.plateNumber like %?1% and v.active=false ORDER BY v.id ASC")
	Page<VehicleMaster> findByVehiclePlateNumbers(String plateNumber,Pageable page);

}
