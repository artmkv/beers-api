package com.solbegsoft.beersapi.controllers;


import com.solbegsoft.beersapi.models.RootBeer;
import com.solbegsoft.beersapi.repositories.ResponseApi;
import com.solbegsoft.beersapi.services.BeersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Beers Controller
 */
@RestController
@RequestMapping("beers-api/v1/beers")
@RequiredArgsConstructor
public class BeersController {

    /**
     * @see BeersService
     */
    private final BeersService beersService;

    /**
     * Find beers
     *
     * @param beerName returns all beers with name of beer
     * @param foodName returns all beers with name of food
     * @param abvLt    returns all beers with ABV less than the supplied number
     * @param abvGt    returns all beers with ABV greater than the supplied number
     * @param ebcLt    returns all beers with EBC less than the supplied number
     * @param ebcGt    returns all beers with EBC greater than the supplied number
     * @param ibuLt    returns all beers with IBU less than the supplied number
     * @param ibuGt    returns all beers with IBU greater than the supplied number
     * @return ResponseApi
     */
    @GetMapping
    public ResponseApi<List<RootBeer>> findBeers(
            @RequestParam(value = "name", required = false) String beerName,
            @RequestParam(value = "food", required = false) String foodName,
            @RequestParam(value = "abv_gt", required = false) String abvGt,
            @RequestParam(value = "ibu_gt", required = false) String ibuGt,
            @RequestParam(value = "ebc_gt", required = false) String ebcGt,
            @RequestParam(value = "abv_lt", required = false) String abvLt,
            @RequestParam(value = "ibu_lt", required = false) String ibuLt,
            @RequestParam(value = "ebc_lt", required = false) String ebcLt
    ) {
        Map<String, String> params = new HashMap<>();
        if (Objects.nonNull(beerName)) {
            params.put("beer_name", beerName);
        }
        if (Objects.nonNull(foodName)) {
            params.put("food", foodName);
        }
        if (Objects.nonNull(abvGt)) {
            params.put("abv_gt", abvGt);
        }
        if (Objects.nonNull(ibuGt)) {
            params.put("ibu_gt", ibuGt);
        }
        if (Objects.nonNull(ebcGt)) {
            params.put("ebc_gt", ebcGt);
        }
        if (Objects.nonNull(abvLt)) {
            params.put("abv_lt", abvLt);
        }
        if (Objects.nonNull(ibuLt)) {
            params.put("ibu_lt", ibuLt);
        }
        if (Objects.nonNull(ebcLt)) {
            params.put("ebc_lt", ebcLt);
        }
        if (params.isEmpty()) {
            List<RootBeer> beers = beersService.getAllBeers();
            return new ResponseApi<>(beers);
        } else {
            List<RootBeer> beers = beersService.getBeers(params);
            return new ResponseApi<>(beers);
        }
    }
}