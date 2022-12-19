package com.solbegsoft.beersapi.rabbit;


/**
 * Async Exception
 */
public class RabbitException extends RuntimeException{

    public RabbitException(String message) {
        super(message);
    }
}