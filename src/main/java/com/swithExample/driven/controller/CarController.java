package com.swithExample.driven.controller;


import com.swithExample.driven.controller.helper.CarHelper;
import com.swithExample.driven.controller.request.CreateCarRequest;
import com.swithExample.driven.controller.request.UpdateCarRequest;
import com.swithExample.driven.controller.response.RestApiResponse;
import com.swithExample.driven.exception.domain.UserNotFoundException;
import com.swithExample.driven.models.Car;
import com.swithExample.driven.services.CarService;
import com.swithExample.driven.util.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/car")
public class CarController {
    private final CarHelper helper;
    private final CarService carService;
    private final ResponseUtil responseUtil;


    public CarController(CarHelper helper, CarService carService, ResponseUtil responseUtil) {
        this.helper = helper;
        this.carService = carService;
        this.responseUtil = responseUtil;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<RestApiResponse> createCar(@RequestBody CreateCarRequest request) {
        Car car = helper.createCar(request);
        carService.saveCar(car);
        return responseUtil.successResponse(car);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<RestApiResponse> updateCar(@PathVariable Long id,@RequestBody UpdateCarRequest request) throws UserNotFoundException {
        final Car car = carService.getCarById(id);
        helper.updateCar(request,car);
        carService.saveCar(car);
        return responseUtil.successResponse(car);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<RestApiResponse> deleteCar(@PathVariable Long id) throws UserNotFoundException {
       carService.deleteCar(id);
        return responseUtil.successResponse("Delete successfully!");
    }

}
