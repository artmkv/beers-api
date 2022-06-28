package com.solbegsoft.beersapi.services;

import com.solbegsoft.beersapi.models.RootBeer;
import com.solbegsoft.beersapi.models.requests.RequestRootBeerDto;
import com.solbegsoft.beersapi.models.response.PunkApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation {@link BeersService}
 */

@Service
@RequiredArgsConstructor
public class BeersServiceImpl implements BeersService {
    /**
     * Punk Api Repository
     */
    private final PunkApiResponse repository;

    @Override
    public List<RootBeer> getBeers(RequestRootBeerDto requestRootBeerDto){
        return repository.getRootBeers(requestRootBeerDto);
    }
}
