package com.solbegsoft.beersapi.models.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * RootBeerDto
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RootBeerDto {

    /**
     * id
     */
    private long id;
    /**
     * name
     */
    private String name;
    /**
     * tagline
     */
    private String tagline;
    /**
     * Date of First brewed
     */
    private LocalDate firstBrewed;
    /**
     * Description
     */
    private String description;
    /**
     * Image URL
     */
    private String imageUrl;
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
    private double targetFg;
    /**
     * Target_fg
     */
    private double targetOg;
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
    private double attenuationLevel;
    /**
     * food array
     */
    private ArrayList<String> foodPairing;
    /**
     * Brewers tips
     */
    private String brewersTips;
    /**
     * Contributed by
     */
    private String contributedBy;
}