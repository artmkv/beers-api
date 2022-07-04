package com.solbegsoft.beersapi.exceptions;


import org.springframework.stereotype.Component;

/**
 * Class with constants to find error message in resource bundle
 */
@Component
public final class ErrorMessageConstant {

    /**
     * Private constructor
     */
    private ErrorMessageConstant(){
    }

    /**
     * Error constants in PunkApi
     */
    public static final String INVALID_TYPE_PARAMETER = "validate.type";
    public static final String INVALID_RANGE_PARAMETER = "validate.range";
    public static final String ERROR_IN_PUNKAPI_REPOSITORY ="error.repository";
    public static final String ERROR_BEERS_API = "error.beersapi";
    public static final String ERROR_CONVERTER = "error.converter";
}