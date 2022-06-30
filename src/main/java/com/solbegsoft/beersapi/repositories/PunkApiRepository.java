package com.solbegsoft.beersapi.repositories;


import com.solbegsoft.beersapi.models.RootBeer;
import com.solbegsoft.beersapi.models.requests.RequestRootBeerDto;
import java.util.List;

public interface PunkApiRepository {

    /**
     * Find all beers
     *
     * @param requestParams object with all parameters
     * @return {@link RequestRootBeerDto}
     */
    List<RootBeer> getRootBeers(RequestRootBeerDto requestParams);
}