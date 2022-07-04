package com.solbegsoft.beersapi.controllers;


import com.solbegsoft.beersapi.annotations.CustomLogger;
import com.solbegsoft.beersapi.annotations.CustomRequestParamValidation;
import com.solbegsoft.beersapi.exceptions.ResponseBeersException;
import com.solbegsoft.beersapi.models.dto.RootBeerDto;
import com.solbegsoft.beersapi.models.requests.RequestRootBeerDto;
import com.solbegsoft.beersapi.models.response.ResponseApi;
import com.solbegsoft.beersapi.services.BeersService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

/**
 * Beers Controller
 */
@Slf4j
@Validated
@RestController
@RequestMapping("beers-api/v1/beers")
@RequiredArgsConstructor
public class BeersController {

    /**
     * @see BeersService
     */
    private final BeersService beersService;

    /**
     * Find Beers
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
    @CustomLogger
    @GetMapping
    public ResponseApi<List<RootBeerDto>> findBeers(
            @RequestParam(value = "lang", required = false) String locale,
            @RequestParam(value = "beerName", required = false) String beerName,
            @RequestParam(value = "foodName", required = false) String foodName,
            @RequestParam(value = "abv_gt", required = false) @CustomRequestParamValidation Double abvGt,
            @RequestParam(value = "ibu_gt", required = false) @CustomRequestParamValidation Double ibuGt,
            @RequestParam(value = "ebc_gt", required = false) @CustomRequestParamValidation Double ebcGt,
            @RequestParam(value = "abv_lt", required = false) @CustomRequestParamValidation Double abvLt,
            @RequestParam(value = "ibu_lt", required = false) @CustomRequestParamValidation Double ibuLt,
            @RequestParam(value = "ebc_lt", required = false) @CustomRequestParamValidation Double ebcLt
    ) throws ResponseBeersException {

        LocaleContextHolder.setLocale(new Locale(locale));
        RequestRootBeerDto requestDto = RequestRootBeerDto.builder()
                .beerName(beerName)
                .foodName(foodName)
                .abvGt(abvGt)
                .abvLt(abvLt)
                .ebcGt(ebcGt)
                .ebcLt(ebcLt)
                .ibuGt(ibuGt)
                .ibuLt(ibuLt)
                .build();
        return new ResponseApi<>(beersService.getBeers(requestDto));
    }
}