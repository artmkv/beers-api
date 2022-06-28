package com.solbegsoft.beersapi.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResponseBeersApiException {

    @ExceptionHandler(Exception.class)
    public String handlerException(Exception e){

        return e.getMessage();
    }
}
