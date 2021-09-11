package com.swithExample.driven.controller.request;


import com.swithExample.driven.util.ParamError;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateCarRequest {
    private String name;
    @NotBlank(message = ParamError.FIELD_NAME)
    private double price;
    @NotBlank(message = ParamError.FIELD_NAME)
    private int type;
    private int rating;
    @NotBlank(message = ParamError.FIELD_NAME)
    private String licensePlate;
    @NotBlank(message = ParamError.FIELD_NAME)
    private int seatCount;
    @NotBlank(message = ParamError.FIELD_NAME)
    private String manufacturer;
}
