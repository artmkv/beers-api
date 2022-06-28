package com.solbegsoft.beersapi.repositories;

import com.solbegsoft.beersapi.models.RootBeer;
import com.solbegsoft.beersapi.models.dtos.BeerDto;


import org.springframework.stereotype.Repository;


@Repository
public interface PunkApiRepository {

    /**
     * Find by name
     *
     * @param name Beer name
     * @return {@link RootBeer}
     */
    BeerDto findByName(String name);

    /**
     * Exists by name
     *
     * @param name beer name
     * @return true/false
     */
    boolean existsByName(String name);

}