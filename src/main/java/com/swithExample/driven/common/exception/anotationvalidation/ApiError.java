package com.swithExample.driven.common.exception.anotationvalidation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {

    int status;

    String message;

    ZonedDateTime time;

    String path;

    String validationErrors;

    public ApiError(int status, String message, String path) {
        super();
        this.time = ZonedDateTime.now(ZoneId.of("Z"));
        this.status = status;
        this.message = message;
        this.path = path;
    }

}
