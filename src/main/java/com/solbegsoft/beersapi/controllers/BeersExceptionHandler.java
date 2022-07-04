package com.solbegsoft.beersapi.controllers;


import com.solbegsoft.beersapi.annotations.CustomLogger;
import com.solbegsoft.beersapi.exceptions.ErrorResponseApi;
import com.solbegsoft.beersapi.exceptions.ResponseBeersException;
import com.solbegsoft.beersapi.models.response.ResponseApi;
import com.solbegsoft.beersapi.exceptions.ErrorMessageConstant;
import com.solbegsoft.beersapi.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.Locale;

/**
 * Exception handler
 */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class BeersExceptionHandler {

    /**
     * @see MessageUtils
     */
    private final MessageUtils messageUtils;

    /**
     * Handle {@link Exception}
     *
     * @param e exception
     * @return {@link ResponseApi}
     */
    @CustomLogger
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ResponseBeersException.class)
    public ErrorResponseApi<Object> handlerResponseBeersException(ResponseBeersException e) {
        return ErrorResponseApi.builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(messageUtils.getMessage(e.getMessageKey(),  Locale.getDefault()))
                .data(e.getHttpStatus() +" : "+ e.getMessage())
                .build();
    }

    /**
     * Handle {@link MethodArgumentTypeMismatchException}
     *
     * @param e exception
     * @return {@link ErrorResponseApi}
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ErrorResponseApi<Object> handlerPunkApiException(MethodArgumentTypeMismatchException e) {
        return ErrorResponseApi.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(String.format(messageUtils.getMessage(
                        ErrorMessageConstant.INVALID_TYPE_PARAMETER, Locale.getDefault()), e.getName()))
                .data(e.getMessage())
                .build();
    }

    /**
     * Handle {@link ConstraintViolationException}
     *
     * @param e exception
     * @return {@link ErrorResponseApi}
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResponseApi<Object> handleConstraintViolationException(ConstraintViolationException e) {
        return ErrorResponseApi.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(messageUtils.getMessage(ErrorMessageConstant.INVALID_RANGE_PARAMETER,  Locale.getDefault()))
                .data(e.getMessage())
                .build();
    }
}