package com.swithExample.driven.common.exception.errors;


public class MyBadRequestException extends RuntimeException{

    public MyBadRequestException(String message) {
        super(message);
    }

    public MyBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
