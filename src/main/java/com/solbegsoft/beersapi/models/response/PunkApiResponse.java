package com.solbegsoft.beersapi.models.response;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


import com.solbegsoft.beersapi.models.RootBeer;
import com.solbegsoft.beersapi.models.requests.RequestRootBeerDto;

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
public class PunkApiResponse {

//    private final RestTemplate restTemplate;

    @Value("${api.punkapi.url}")
    private String urlPunkApi;

    public List<RootBeer> getRootBeers(RequestRootBeerDto requestParams) {
        RestTemplate restTemplate = new RestTemplate();
        URI url = getUriFromRootBeerDto(requestParams);
        ResponseEntity<RootBeer[]> forEnt = restTemplate.getForEntity(url, RootBeer[].class);
        return new ArrayList<>(Arrays.asList(Objects.requireNonNull(forEnt.getBody())));
    }

    /**
     * Create URI from {@link RequestRootBeerDto}
     *
     * @param requestParams
     * @return URI
     */
    private URI getUriFromRootBeerDto(RequestRootBeerDto requestParams) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlPunkApi);
        builder = setQueryParamIfNotNullParam(builder, "beer_name", requestParams.getBeerName());
        builder = setQueryParamIfNotNullParam(builder, "food", requestParams.getFoodName());
        builder = setQueryParamIfNotNullParam(builder, "abv_gt", requestParams.getAbvGt());
        builder = setQueryParamIfNotNullParam(builder, "abv_lt", requestParams.getAbvLt());
        builder = setQueryParamIfNotNullParam(builder, "ibu_gt", requestParams.getIbuGt());
        builder = setQueryParamIfNotNullParam(builder, "ibu_lt", requestParams.getIbuLt());
        builder = setQueryParamIfNotNullParam(builder, "ebc_gt", requestParams.getEbcGt());
        builder = setQueryParamIfNotNullParam(builder, "ebc_lt", requestParams.getEbcLt());
        return builder.build().toUri();
    }

    private UriComponentsBuilder setQueryParamIfNotNullParam(UriComponentsBuilder builder, String name, Object value){
        if(Objects.nonNull(value)){
            return builder.queryParam(name, value);
        }
        return builder;
    }
}
