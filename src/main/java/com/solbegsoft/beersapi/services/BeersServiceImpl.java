package com.solbegsoft.beersapi.services;

import com.solbegsoft.beersapi.models.RootBeer;
import com.solbegsoft.beersapi.models.requests.RequestRootBeerDto;
import com.solbegsoft.beersapi.repositories.PunkApiRepository;
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
    private final PunkApiRepository punkApiRepository;

    @Override
    public List<RootBeer> getBeers(RequestRootBeerDto requestRootBeerDto) {
        return punkApiRepository.getRootBeers(requestRootBeerDto);
    }


}
