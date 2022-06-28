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

    private String beerName;
    private String foodName;
    private Double abvGt;
    private Double abvLt;
    private Double ibuGt;
    private Double ibuLt;
    private Double ebcGt;
    private Double ebcLt;

}



