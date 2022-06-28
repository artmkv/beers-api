package com.solbegsoft.beersapi.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Beer dto
 */
//@Data
////@Builder
//@NoArgsConstructor
public class BeerDto {

    /**
     * id
     */
    private UUID id;
    /**
     * name
     */
    private String name;
    /**
     * tagline
     */
    private String tagline;
    /**
     * First brewed
     */
    private String first_brewed;
    /**
     * Description
     */
    private String description;
    /**
     * Image URL
     */
    private String image_url;
    /**
     * ABV
     */
    private double abv;
    /**
     * IBU
     */
    private double ibu;
    /**
     * Target_fg
     */
    private double target_fg;
    /**
     * Target_fg
     */
    private double target_og;
    /**
     * EBC
     */
    private double ebc;
    /**
     * SRM
     */
    private double srm;
    /**
     * PH
     */
    private double ph;
    /**
     * Attenuation level
     */
    private double attenuation_level;
    /**
     * @see FoodDto array
     */
    private ArrayList<FoodDto> food_pairing;
    /**
     * Brewers tips
     */
    private String brewers_tips;
    /**
     * Contributed by
     */
    private String contributed_by;
}

