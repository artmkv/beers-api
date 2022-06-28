package com.solbegsoft.beersapi.controllers;

import com.solbegsoft.beersapi.models.response.ResponseApi;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Exception handler
 */
@RestControllerAdvice
public class BeersExceptionHandler {

    /**
     * Handle {@link Exception}
     *
     * @param e exception
     * @return {@link ResponseApi}
     */
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(Exception.class)
    public ResponseApi<String> handlerException(Exception e) {

        return new ResponseApi<>("Sorry! This is EXCEPTION");
    }
}
