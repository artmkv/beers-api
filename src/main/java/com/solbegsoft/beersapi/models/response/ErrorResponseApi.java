package com.solbegsoft.beersapi.models.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class ErrorResponseApi<T> {

    /**
     * Statuscode of HttpStatus
     */
    private int statusCode;

    /**
     *@see HttpStatus
     */
    private HttpStatus httpStatus;

    /**
     * Message of Error
     */
    private String message;

    /**
     * Data of message
     */
    private T data;
}