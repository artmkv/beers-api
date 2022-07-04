package com.solbegsoft.beersapi.models;


import com.solbegsoft.beersapi.models.dto.RootBeerDto;
import com.solbegsoft.beersapi.utils.FirstBrewedConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Mapper RootBeer to RootBeerDto
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RootBeerMapper {

    /**
     * @see FirstBrewedConverter
     */
    private final FirstBrewedConverter converter;

    /**
     * Mapped RootBeer to RootBeerDto
     *
     * @param rootBeer with request parameters
     * @return {@link RootBeerDto}
     */
    public RootBeerDto mapToDto(RootBeer rootBeer) {
        if (Objects.nonNull(rootBeer)) {
            return RootBeerDto.builder()
                    .id(rootBeer.getId())
                    .name(rootBeer.getName())
                    .tagline(rootBeer.getTagline())
                    .firstBrewed(converter.convertStringToLocalDate(rootBeer.getFirstBrewed()))
                    .description(rootBeer.getDescription())
                    .imageUrl(rootBeer.getImageUrl())
                    .abv(rootBeer.getAbv())
                    .ibu(rootBeer.getIbu())
                    .targetFg(rootBeer.getTargetFg())
                    .targetOg(rootBeer.getTargetOg())
                    .ebc(rootBeer.getEbc())
                    .srm(rootBeer.getSrm())
                    .ph(rootBeer.getPh())
                    .attenuationLevel(rootBeer.getAttenuationLevel())
                    .foodPairing(rootBeer.getFoodPairing())
                    .brewersTips(rootBeer.getBrewersTips())
                    .contributedBy(rootBeer.getContributedBy())
                    .build();
        }
        return null;
    }

    /**
     * Mapped List of RootBeer to List of RootBeerDto
     *
     * @param list List of RootBeer
     * @return {@link List} RootBeerDto
     */
    public List<RootBeerDto> mapListToDto(List<RootBeer> list) {

        return list.stream()
                .map(this::mapToDto)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}