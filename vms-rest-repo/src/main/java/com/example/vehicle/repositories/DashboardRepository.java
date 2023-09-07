package com.example.vehicle.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.vehicle.entities.Dashboard;

@Repository
public interface DashboardRepository extends CrudRepository<Dashboard, Integer> {

	@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM vehicle_master where is_deleted = 0")
	public int getCountOfActiveVehicle();
	
	@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM vehicle_master where is_deleted = 1")
	public int getCountOfNonActiveVehicle();
	
	@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM driver_master where is_deleted = 0")
	public int getCountOfActiveDriver();
	
	@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM driver_master where is_deleted = 1")
	public int getCountOfNonActiveDriver();
}
