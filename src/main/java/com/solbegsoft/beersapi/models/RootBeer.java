package com.solbegsoft.beersapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RootBeer {

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
     * First brewed
     */
    @JsonProperty("first_brewed")
    private String firstBrewed;
    /**
     * Description
     */
    private String description;
    /**
     * Image URL
     */
    @JsonProperty("image_url")
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
    @JsonProperty("target_fg")
    private double targetFg;
    /**
     * Target_fg
     */
    @JsonProperty("target_og")
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
    @JsonProperty("attenuation_level")
    private double attenuationLevel;
    /**
     * food array
     */
    @JsonProperty("food_pairing")
    private ArrayList<String> foodPairing;
    /**
     * Brewers tips
     */
    @JsonProperty("brewers_tips")
    private String brewersTips;
    /**
     * Contributed by
     */
    @JsonProperty("contributed_by")
    private String contributedBy;
}