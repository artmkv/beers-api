package com.solbegsoft.beersapi.utils;


import com.solbegsoft.beersapi.models.RootBeer;
import com.solbegsoft.beersapi.models.dto.RootBeerDto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Converter {@link RootBeer} and {@link RootBeerDto}
 */
@Mapper(componentModel = "spring", uses = FirstBrewedConverter.class)
public interface RootBeerMapper {

    /**
     * Convert {@link RootBeer} to {@link RootBeerDto}
     *
     * @param rootBeer {@link RootBeer}
     * @return {@link RootBeerDto}
     */
    RootBeerDto mapRootBeerToDto(RootBeer rootBeer);

    /**
     * Convert {@link RootBeerDto} to{@link RootBeer}
     *
     * @param dto {@link RootBeerDto}
     * @return {@link RootBeer}
     */
    RootBeer mapDtoToRootBeer(RootBeerDto dto);

    /**
     * Convert List of RootBeer to List of RootBeerDto
     *
     * @param list List of RootBeer
     * @return {@link List} {@link RootBeerDto}
     */
    List<RootBeerDto> mapListToDto(List<RootBeer> list);

}