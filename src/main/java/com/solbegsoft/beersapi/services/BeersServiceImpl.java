package com.solbegsoft.beersapi.services;


import com.solbegsoft.beersapi.models.dto.RootBeerDto;
import com.solbegsoft.beersapi.models.RootBeerMapper;
import com.solbegsoft.beersapi.models.requests.RequestRootBeerDto;
import com.solbegsoft.beersapi.repositories.PunkApiRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation {@link BeersService}
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BeersServiceImpl implements BeersService {

    /**
     * @see PunkApiRepository
     */
    private final PunkApiRepository punkApiRepository;

    /**
     * @see RootBeerMapper
     */
    private final RootBeerMapper mapper;

    /**
     * Get beer with pagination
     *
     * @param requestRootBeerDto request parameters
     * @return List of {@link RootBeerDto} with mapping from lists RootBeer
     */
    @Override
    public List<RootBeerDto> getBeers(RequestRootBeerDto requestRootBeerDto) {
        return mapper.mapListToDto(punkApiRepository.getRootBeers(requestRootBeerDto));
    }
}