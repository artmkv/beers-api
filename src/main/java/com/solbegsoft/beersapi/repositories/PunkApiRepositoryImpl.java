package com.solbegsoft.beersapi.repositories;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.solbegsoft.beersapi.exceptions.ResponseBeersException;
import com.solbegsoft.beersapi.models.RootBeer;
import com.solbegsoft.beersapi.models.requests.RequestRootBeerDto;

import com.solbegsoft.beersapi.utils.ErrorMessageUtils;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Response model
 */
@Data
@Component
@RequiredArgsConstructor
public class PunkApiRepositoryImpl implements PunkApiRepository {

    private final RestTemplate restTemplate;

    @Value("${api.punkapi.url}")
    private String urlPunkApi;

    /**
     * Find all beers
     *
     * @param requestParams object with all parameters
     * @return {@link RequestRootBeerDto}
     */
    public List<RootBeer> getRootBeers(RequestRootBeerDto requestParams) {
        URI uri = getUriFromRootBeerDto(requestParams);
        ResponseEntity<RootBeer[]> forEnt = restTemplate.getForEntity(uri, RootBeer[].class);
        if (!forEnt.getStatusCode().is2xxSuccessful()) {
            throw new ResponseBeersException(ErrorMessageUtils.ERROR_IN_PUNKAPI_REPOSITORY, forEnt.getStatusCode());
        }
        return new ArrayList<>(Arrays.asList(Objects.requireNonNull(forEnt.getBody())));
    }

    /**
     * Create URI from {@link RequestRootBeerDto}
     *
     * @param requestParams object with all parameters
     * @return URI
     */
    private URI getUriFromRootBeerDto(RequestRootBeerDto requestParams) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlPunkApi);
        return builder
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
