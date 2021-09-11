package com.swithExample.driven.repos;

import com.swithExample.driven.models.Car;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface CarRepository extends PagingAndSortingRepository<Car, Long> {
    Car findOneById(Long id);

    List<Car> findAllByIsAvailable(boolean isAvailable);
    Car findOneByUsername(String username);
}
