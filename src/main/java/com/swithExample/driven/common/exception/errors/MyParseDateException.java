package com.swithExample.driven.common.exception.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MyParseDateException extends RuntimeException{
    public MyParseDateException(String message) {
        super(message);
    }

    public MyParseDateException(String message, Throwable cause) {
        super(message, cause);
    }
}
