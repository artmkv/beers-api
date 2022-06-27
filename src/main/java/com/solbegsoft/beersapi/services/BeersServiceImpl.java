package com.solbegsoft.beersapi.services;

import com.solbegsoft.beersapi.models.RootBeer;
import com.solbegsoft.beersapi.repositories.PunkApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public List<RootBeer> getBeers(Map<String, String> params) {
        List<RootBeer> result = repository.getRootBeers(params);
        return result;
    }

    @Override
    public List<RootBeer> getAllBeers() {
        List<RootBeer> resultAll = repository.getAllBeers();
        return resultAll;
    }
}
