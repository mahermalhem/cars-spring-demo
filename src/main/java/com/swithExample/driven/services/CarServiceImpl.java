package com.swithExample.driven.services;


import com.swithExample.driven.exception.domain.UserNotFoundException;
import com.swithExample.driven.models.Car;
import com.swithExample.driven.repos.CarRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car saveCar(Car car) {
        System.out.println(car);
        return carRepository.save(car);
    }

    @Override
    public List<Car> getAllAvailableCars() {
        try {
            return carRepository.findAllByIsAvailable(true);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Car> getAllCars() {
        try {
            return (List<Car>) carRepository.findAll();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void deleteCar(Long id) throws UserNotFoundException {
        final Car car = carRepository.findOneById(id);
        if (car == null)
            throw new UserNotFoundException(String.format("Car with id %s not found", id));
        else car.setActive(false);
        carRepository.save(car);
    }

    @Override
    public boolean existedCar(Long id) {
        try {
            Car car = carRepository.findOneById(id);
            return car != null;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Car getCarById(Long id) throws UserNotFoundException {
        final Car car = carRepository.findOneById(id);
        if (car == null)
            throw new UserNotFoundException(String.format("Car with id %s not found", id));
        else return car;
    }

    @Override
    public boolean isCarAvailable() {
        try {
            List<Car> cars = carRepository.findAllByIsAvailable(true);
            return cars != null && cars.size() != 0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Car getCarByUsername(String username) {
        return carRepository.findOneByUsername(username);
    }
}
