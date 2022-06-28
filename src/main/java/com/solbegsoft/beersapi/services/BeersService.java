package com.solbegsoft.beersapi.services;


import com.solbegsoft.beersapi.models.RootBeer;
import com.solbegsoft.beersapi.models.requests.RequestRootBeerDto;

import java.util.List;

/**
 * Service for beers
 */
public interface BeersService {

    /**
     * Get beer with pagination
     *
     * @param requestRootBeerDto
     * @return list of {@link RootBeer}
     */
    List<RootBeer> getBeers(RequestRootBeerDto requestRootBeerDto);
}
