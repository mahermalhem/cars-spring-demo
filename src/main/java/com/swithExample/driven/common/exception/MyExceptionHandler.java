package com.swithExample.driven.common.exception;

import com.swithExample.driven.common.exception.errors.MyBadRequestException;
import com.swithExample.driven.common.exception.errors.MyExistedException;
import com.swithExample.driven.common.exception.errors.MyNotFoundException;
import com.swithExample.driven.common.exception.errors.MyParseDateException;
import com.swithExample.driven.jwt.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(MyNotFoundException.class)
    public ResponseEntity<HttpResponse> notfound(MyNotFoundException e) {
        return getHttpResponse(e, NOT_FOUND);
    }

    @ExceptionHandler(MyExistedException.class)
    public ResponseEntity<HttpResponse> existed(MyExistedException e) {
        return getHttpResponse(e, METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(MyBadRequestException.class)
    public ResponseEntity<HttpResponse> existed(MyBadRequestException e) {
        return getHttpResponse(e, BAD_REQUEST);
    }

    @ExceptionHandler(MyParseDateException.class)
    public ResponseEntity<HttpResponse> invalidToken(MyParseDateException e) {
        return getHttpResponse(e, NOT_FOUND);
    }

    private ResponseEntity<HttpResponse> getHttpResponse(RuntimeException e, HttpStatus status) {
        HttpResponse httpResponse =
                new HttpResponse(status.value(), status, status.getReasonPhrase().toUpperCase(), e.getMessage().toUpperCase());
        return new ResponseEntity<>(httpResponse, status);
    }

}
