package com.solbegsoft.beersapi.controllers;


import com.solbegsoft.beersapi.models.dtos.BeerDto;
import com.solbegsoft.beersapi.repositories.ResponseApi;
import com.solbegsoft.beersapi.services.BeersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * @return ResponseApi
     */
    @GetMapping
    public ResponseApi<List<BeerDto>> findBeers(
            @RequestParam String name,
            @RequestParam String food,
            @RequestParam Integer abv,
            @RequestParam Integer ibu,
            @RequestParam Integer ebc
    ) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("food", food);
        params.put("abv", abv);
        params.put("ibu", ibu);
        params.put("ebc", ebc);
        List<BeerDto> beers = beersService.getBeers(params);
        return new ResponseApi<>(beers);
    }
}