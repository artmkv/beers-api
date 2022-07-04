package com.solbegsoft.beersapi.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

/**
 * General Exception in beer-api service
 */
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
public class ResponseBeersException extends RuntimeException {

    /**
     * @see HttpStatus
     */
    private final HttpStatus httpStatus;
    /**
     * message key from localized message resource
     */
    private final String messageKey;

    /**
     * Constructor
     *
     * @param messageKey message key
     * @param httpStatus {@link HttpStatus}
     */
    public ResponseBeersException(String messageKey, HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.messageKey = messageKey;
    }

    /**
     * Constructor
     *
     * @param messageKey message key
     * @param httpStatus {@link HttpStatus}
     * @param message
     */
    public ResponseBeersException(String messageKey, HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.messageKey = messageKey;
    }

    /**
     * Constructor
     *
     * @param messageKey message key
     * @param httpStatus {@link HttpStatus}
     * @param message
     * @param cause
     */
    public ResponseBeersException(String messageKey, HttpStatus httpStatus, String message, Throwable cause) {
        super(message, cause);
        this.httpStatus = httpStatus;
        this.messageKey = messageKey;
    }
}