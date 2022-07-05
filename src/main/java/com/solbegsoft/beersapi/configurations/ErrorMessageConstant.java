package com.solbegsoft.beersapi.configurations;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Class with constants to find error message in resource bundle
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorMessageConstant {

    /**
     * Error constants in PunkApi
     */
    public static final String INVALID_TYPE_PARAMETER = "validate.type";
    public static final String INVALID_RANGE_PARAMETER = "validate.range";
    public static final String ERROR_IN_PUNKAPI_REPOSITORY ="error.repository";
    public static final String ERROR_BEERS_API = "error.beersapi";
    public static final String ERROR_CONVERTER = "error.converter";
}