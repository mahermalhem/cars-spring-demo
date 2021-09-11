package com.swithExample.driven.controller.helper;

import com.swithExample.driven.common.enums.EngineType;
import com.swithExample.driven.common.exception.anotationvalidation.BadRequestException;
import com.swithExample.driven.controller.request.CreateCarRequest;
import com.swithExample.driven.controller.request.UpdateCarRequest;
import com.swithExample.driven.models.Car;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class CarHelper {

    public Car createCar(CreateCarRequest request) {
        final Car car = new Car();
        System.out.println(request.getLicensePlate());
        if (request.getType() == 0)
            car.setEngineType(EngineType.ELECTRIC);
        else if (request.getType() == 1)
            car.setEngineType(EngineType.GAS);
        else
            throw new BadRequestException("The type should be less then 2");
        car.setName(request.getName());
        car.setPrice(request.getPrice());
        car.setRating(request.getRating());
        car.setLicensePlate(request.getLicensePlate());
        car.setSeatCount(request.getSeatCount());
        car.setActive(true);
        car.setManufacturer(request.getManufacturer());
        car.setAvailable(true);
        car.setConvertible(true);
        return car;
    }

    public void updateCar(UpdateCarRequest request, Car car) {
// check validation here
        car.setActive(true);
        car.setAvailable(true);
        car.setManufacturer(request.getManufacturer());
        car.setConvertible(true);
        if (request.getType() == 0)
            car.setEngineType(EngineType.ELECTRIC);
        else if (request.getType() == 1)
            car.setEngineType(EngineType.GAS);
        else
            throw new BadRequestException("The type should be less then 2");
        car.setName(request.getName());
        car.setPrice(request.getPrice());
        car.setRating(request.getRating());
        car.setLicensePlate(request.getLicensePlate());
        car.setSeatCount(request.getSeatCount());
    }

}

