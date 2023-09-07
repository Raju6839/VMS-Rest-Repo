package com.example.vehicle.repositories;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface MappedTypeRepository<T> extends PagingAndSortingRepository<T, Integer> {

}
