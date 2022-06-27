package com.solbegsoft.beersapi.services;


import com.solbegsoft.beersapi.models.RootBeer;

import java.util.List;
import java.util.Map;

/**
 * Service for beers
 */
public interface BeersService {

    /**
     * Get beer with pagination
     *
     * @param params
     * @return list of {@link RootBeer}
     */
    List<RootBeer> getBeers(Map<String, String> params);

    /**
     * Get all beer with pagination
     *
     * @return list of {@link RootBeer}
     */
    List<RootBeer> getAllBeers();
}
