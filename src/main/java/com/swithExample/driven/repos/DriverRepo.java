package com.swithExample.driven.repos;


import com.swithExample.driven.models.Driver;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepo extends PagingAndSortingRepository<Driver, Long> {
//    boolean getCarByAvailableTrue();
}
