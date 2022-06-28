package com.solbegsoft.beersapi.models.dtos;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Food dto
 */
//@Data
//@NoArgsConstructor
////@Builder
public class FoodDto {

    /**
     * id
     */
    private UUID id;

    /**
     * name
     */
    private String text;

    /**
     * Array of {@link }
     */
    private ArrayList<BeerDto> beers;
}
