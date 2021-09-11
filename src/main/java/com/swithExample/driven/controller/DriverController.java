package com.swithExample.driven.controller;


import com.swithExample.driven.common.exception.anotationvalidation.BadRequestException;
import com.swithExample.driven.controller.response.RestApiResponse;
import com.swithExample.driven.exception.domain.UserNotFoundException;
import com.swithExample.driven.models.Car;
import com.swithExample.driven.models.CarStatusRequestValue;
import com.swithExample.driven.services.CarService;
import com.swithExample.driven.util.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/driver")
public class DriverController {
    private final CarService carService;
    private final ResponseUtil responseUtil;

    public DriverController(CarService carService, ResponseUtil responseUtil) {
        this.carService = carService;
        this.responseUtil = responseUtil;
    }

    @PutMapping(value = "changeStatus{id}")
    public ResponseEntity<RestApiResponse> changeCarStatus(@PathVariable Long id, @RequestBody CarStatusRequestValue requestValue) throws UserNotFoundException {
        if (!carService.existedCar(id))
            throw new BadRequestException("The car Id does not exist!");
        else {
            if (carService.isCarAvailable()) {
                Car car = carService.getCarById(id);
                car.setAvailable(requestValue.isStatus());
                final String username = SecurityContextHolder.getContext().getAuthentication().getName();
                car.setUsername(username);
                carService.saveCar(car);
            }
        }
        return responseUtil.successResponse("OK");
    }

    @GetMapping
    public ResponseEntity<RestApiResponse> getAllAvailableCars(@RequestParam("type") String type) throws UserNotFoundException {
        if (type == null) {
            List<Car> cars = carService.getAllCars();
            return responseUtil.successResponse(cars);
        }

        if (type.equals("available")) {
            List<Car> cars = carService.getAllAvailableCars();
            return responseUtil.successResponse(cars);
        } else {
            List<Car> cars = carService.getAllCars();
            return responseUtil.successResponse(cars);
        }
    }
//    @PutMapping("{id}")
//    public ResponseEntity<RestApiResponse> deSelectCar(@PathVariable Long id) throws UserNotFoundException {
//        if (!carService.existedCar(id))
//            throw new BadRequestException("The car Id does not exist!");
//        else {
//            if ()
//        }
//        return responseUtil.successResponse("OK");
//    }
}
