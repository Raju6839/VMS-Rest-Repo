package com.example.vehicle.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.vehicle.entities.DriverMaster;

@Repository
public interface DriverMasterRepository extends JpaRepository<DriverMaster, Integer> {

	@Query("select d from DriverMaster d where d.id=?1 and d.active = false")
//	@Query(nativeQuery = true, value = "select * from driver_master where id=?1")
    DriverMaster findByDriverId(int id);
	
	@Query(nativeQuery = true, value = "select * from driver_master where is_Deleted = 'false'")
	List<DriverMaster> findAll();
	
	@Query("select d from DriverMaster d where d.id=?1 and d.active = false")
	public Optional<DriverMaster> findById(int id);
	
	@Query("select d from DriverMaster d where d.active = false ORDER BY d.id ASC")
	Page<DriverMaster> findAllpagination(Pageable page);
	
	@Query("select d from DriverMaster d where d.firstname Like %?1% and d.active = false ORDER BY d.id ASC")
    Page<DriverMaster> findPageByFirstName(String firstname,Pageable page);
	
	 @Query("select d from DriverMaster d where d.licenseNumber=?1")
	 Optional<DriverMaster> findByLicenseNumber(String driverRegistrationNumberFromUi);
	
//	@Query("select d from DriverMaster d where d.active=true ")
//	List<DriverMaster> findAll();
//
//	@Query("select d from DriverMaster d where d.id=?1 and d.active =true")
//	Optional<DriverMaster> findById(int id);
	
	
}
