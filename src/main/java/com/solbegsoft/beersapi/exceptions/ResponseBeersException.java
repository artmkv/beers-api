package com.solbegsoft.beersapi.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;


/**
 * General Exception in beer-api service
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class ResponseBeersException extends RuntimeException {

    private final int statusCode;
    private final HttpStatus httpStatus;

    /**
     * Constructor
     *
     * @param message message
     */
    public ResponseBeersException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
        this.statusCode = httpStatus.value();
    }
}
