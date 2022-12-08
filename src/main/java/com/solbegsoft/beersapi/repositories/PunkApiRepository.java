package com.solbegsoft.beersapi.repositories;


import com.solbegsoft.beersapi.models.RootBeer;
import com.solbegsoft.beersapi.models.requests.RequestRootBeerDto;

import java.util.List;

/**
 * Interface PunkApi repository
 */
public interface PunkApiRepository {

    /**
     * Find all beers
     *
     * @param requestRootBeerDto {@link RequestRootBeerDto} object with all parameters
     * @return list of {@link RootBeer}
     */
    List<RootBeer> getRootBeers(RequestRootBeerDto requestRootBeerDto);
}