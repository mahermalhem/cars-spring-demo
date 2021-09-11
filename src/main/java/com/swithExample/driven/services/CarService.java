package com.swithExample.driven.services;

import com.swithExample.driven.exception.domain.UserNotFoundException;
import com.swithExample.driven.models.Car;

import java.util.List;

public interface CarService {
    Car saveCar(Car car);

    void deleteCar(Long id) throws UserNotFoundException;

    Car getCarById(Long id) throws UserNotFoundException;

    List<Car> getAllCars();

    List<Car> getAllAvailableCars();

    boolean isCarAvailable();

    Car getCarByUsername(String username);

    boolean existedCar(Long id);
}
