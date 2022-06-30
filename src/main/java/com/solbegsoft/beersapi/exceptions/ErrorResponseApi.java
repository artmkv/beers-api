package com.solbegsoft.beersapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


/**
 * Error Response model
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseApi {

    /**
     * Statuscode of HttpStatus
     */
    private int statusCode;

    /**
     * HttpStatus
     */
    private HttpStatus httpStatus;

    /**
     * Message of Error
     */
    private String message;

}
