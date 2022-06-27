package com.solbegsoft.beersapi.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


import com.solbegsoft.beersapi.models.RootBeer;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Response model
 */
@Data
@AllArgsConstructor
@Component
public class PunkApiResponse {

    public static final int TIMEOUT_MILLIS = 1100;
    private final int MAX_PER_PAGE = 80;
    private final int MAX_ELEMENT_ON_API = 325;
    private final int NUMBER_OF_PAGE = MAX_ELEMENT_ON_API % MAX_PER_PAGE > 0 ? MAX_ELEMENT_ON_API / MAX_PER_PAGE + 1 : MAX_ELEMENT_ON_API % MAX_PER_PAGE;
    private final String URL_PUNKAPI = "https://api.punkapi.com/v2/beers";
    private final String URL_PUNKAPI_FOR_ALL_PAGE = "https://api.punkapi.com/v2/beers?page={page}&per_page={per_page}";


    public List<RootBeer> getRootBeers(Map<String, String> params) {
        List<RootBeer> resultList = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        String url = getResultUrlWithParams(URL_PUNKAPI,params);
        ResponseEntity<RootBeer[]> forEnt = restTemplate.getForEntity(url, RootBeer[].class);
        resultList.addAll(Arrays.asList(forEnt.getBody()));
        return resultList;
    }

    public List<RootBeer> getAllBeers() {
        RestTemplate restTemplate = new RestTemplate();
        List<RootBeer> resultList = new ArrayList<>();
        for (int i = 1; i <= NUMBER_OF_PAGE; i++) {
            ResponseEntity<RootBeer[]> response = restTemplate.getForEntity(URL_PUNKAPI_FOR_ALL_PAGE, RootBeer[].class, i, MAX_PER_PAGE);
            try {
                wait(TIMEOUT_MILLIS);
            } catch (Exception e) {
                e.getMessage();
            }
            resultList.addAll(Arrays.asList(response.getBody()));
        }
        return resultList;
    }

    private static String getResultUrlWithParams(String url, Map<String, String> params) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.queryParam(entry.getKey(), entry.getValue());
        }
        return builder.toUriString();
    }
}
