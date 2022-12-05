package com.solbegsoft.beersapi.models.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Response model
 *
 * @param <T> Generic type
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ResponseApi<T> {

    /**
     * Response data
     */
    private T data;
}