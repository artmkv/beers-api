package com.solbegsoft.beersapi.models.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

/**
 * Error Response model
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ErrorResponseApi<T> extends ResponseApi<T> {

    /**
     * Statuscode of HttpStatus
     */
    private int statusCode;

    /**
     * @see HttpStatus
     */
    private HttpStatus httpStatus;

    /**
     * Message of Error
     */
    private String message;

    /**
     * Response
     *
     * @param data Parameter
     */
    public ErrorResponseApi(T data) {
        super(data);
    }
}