package com.solbegsoft.beersapi.controllers;

import com.solbegsoft.beersapi.exceptions.ErrorResponseApi;
import com.solbegsoft.beersapi.exceptions.ResponseBeersException;
import com.solbegsoft.beersapi.models.response.ResponseApi;
import com.solbegsoft.beersapi.utils.ErrorMessageUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;

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
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ResponseBeersException.class)
    public ErrorResponseApi handlerResponseBeersException(ResponseBeersException e) {
        ErrorResponseApi errorResponseApi = new ErrorResponseApi();
        errorResponseApi.setHttpStatus(e.getHttpStatus());
        errorResponseApi.setMessage(e.getMessage());
        return errorResponseApi;
    }

    /**
     * Handle {@link MethodArgumentTypeMismatchException}
     *
     * @param e exception
     * @return {@link ErrorResponseApi}
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ErrorResponseApi handlerPunkApiException(MethodArgumentTypeMismatchException e) {
        ErrorResponseApi errorResponseApi = new ErrorResponseApi();
        errorResponseApi.setHttpStatus(HttpStatus.BAD_REQUEST);
        errorResponseApi.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorResponseApi.setMessage(String.format(ErrorMessageUtils.INVALID_TYPE_PARAMETER, e.getName(), e.getPropertyName()));
        return errorResponseApi;
    }

    /**
     * Handle {@link ConstraintViolationException}
     *
     * @param e exception
     * @return {@link ErrorResponseApi}
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResponseApi handleConstraintViolationException(ConstraintViolationException e) {
        return new ErrorResponseApi(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, e.getMessage());
    }

}
