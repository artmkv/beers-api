package com.solbegsoft.beersapi.repositories;


import com.solbegsoft.beersapi.configurations.ErrorMessageConstant;
import com.solbegsoft.beersapi.exceptions.ResponseBeersException;
import com.solbegsoft.beersapi.models.RootBeer;
import com.solbegsoft.beersapi.models.requests.RequestRootBeerDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * PunApi repository implemented
 */
@Slf4j
@Data
@Component
@RequiredArgsConstructor
public class PunkApiRepositoryImpl implements PunkApiRepository {

    /**
     * @see RestTemplate
     */
    private final RestTemplate restTemplate;

    /**
     * URL of PunkApi
     */
    @Value("${api.punkapi.url}")
    private String urlPunkApi;

    /**
     * Find all beers
     *
     * @param requestParams object with all parameters
     * @return {@link RequestRootBeerDto}
     */
    public List<RootBeer> getRootBeers(RequestRootBeerDto requestParams) throws ResponseBeersException {

        URI uri = getUriFromRootBeerDto(requestParams);
        ResponseEntity<RootBeer[]> entity = restTemplate.getForEntity(uri, RootBeer[].class);
        if (!entity.getStatusCode().is2xxSuccessful()) {
            throw new ResponseBeersException(ErrorMessageConstant.ERROR_IN_PUNKAPI_REPOSITORY, entity.getStatusCode(), entity.toString());
        }
        return new ArrayList<>(Arrays.asList(Objects.requireNonNull(entity.getBody())));
    }

    /**
     * Create URI from {@link RequestRootBeerDto}
     *
     * @param requestParams object with all parameters
     * @return URI
     */
    private URI getUriFromRootBeerDto(RequestRootBeerDto requestParams) {

        return UriComponentsBuilder.fromHttpUrl(urlPunkApi)
                .queryParamIfPresent("beer_name", Optional.ofNullable(requestParams.getBeerName()))
                .queryParamIfPresent("food", Optional.ofNullable(requestParams.getFoodName()))
                .queryParamIfPresent("abv_gt", Optional.ofNullable(requestParams.getAbvGt()))
                .queryParamIfPresent("abv_lt", Optional.ofNullable(requestParams.getAbvLt()))
                .queryParamIfPresent("ibu_gt", Optional.ofNullable(requestParams.getIbuGt()))
                .queryParamIfPresent("ibu_lt", Optional.ofNullable(requestParams.getIbuLt()))
                .queryParamIfPresent("ebc_gt", Optional.ofNullable(requestParams.getEbcGt()))
                .queryParamIfPresent("ebc_lt", Optional.ofNullable(requestParams.getEbcLt()))
                .build().toUri();
    }
}