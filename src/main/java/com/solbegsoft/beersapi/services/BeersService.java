package com.solbegsoft.beersapi.services;


import com.solbegsoft.beersapi.models.dto.RootBeerDto;
import com.solbegsoft.beersapi.models.requests.RequestRootBeerDto;

import java.util.List;

/**
 * Interface Service for beers
 */
public interface BeersService {

    /**
     * Get beer with pagination
     *
     * @param requestRootBeerDto request parameters
     * @return List of {@link RootBeerDto} with mapping from lists RootBeer
     */
    List<RootBeerDto> getBeers(RequestRootBeerDto requestRootBeerDto);
}