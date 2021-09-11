package com.swithExample.driven.controller.request;


import com.swithExample.driven.util.ParamError;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateCarRequest {
    private String name;
    private double price;
    private int type;
    private int rating;
    private String licensePlate;
    private int seatCount;
    private String manufacturer;
}
