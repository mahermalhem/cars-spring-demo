package com.swithExample.driven.common.exception.errors;

public class MyExistedException extends RuntimeException{
    public MyExistedException(String message) {
        super(message);
    }

    public MyExistedException(String message, Throwable cause) {
        super(message, cause);
    }
}
