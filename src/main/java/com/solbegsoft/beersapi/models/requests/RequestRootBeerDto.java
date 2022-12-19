package com.solbegsoft.beersapi.models.requests;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  Parameters of request
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestRootBeerDto {

    /**
     * Parameter beer name
     */
    private String beerName;
    /**
     * parameter food name
     */
    private String foodName;
    /**
     * abvGT
     */
    private Double abvGt;
    /**
     * abvLt
     */
    private Double abvLt;
    /**
     * ibuGt
     */
    private Double ibuGt;
    /**
     * ibuLt
     */
    private Double ibuLt;
    /**
     * ebcGt
     */
    private Double ebcGt;
    /**
     * ebcLt
     */
    private Double ebcLt;
}