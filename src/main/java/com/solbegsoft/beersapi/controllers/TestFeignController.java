package com.solbegsoft.beersapi.controllers;


import com.solbegsoft.beersapi.models.response.ResponseApi;
import com.solbegsoft.beersapi.repositories.BeersFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Beers Controller
 */
@RestController
@RequestMapping("beers-api/v1/test")
@RequiredArgsConstructor
public class TestFeignController {

    /**
     * @see BeersFeignClient
     */
    private final BeersFeignClient beersFeignClient;

    @GetMapping
    public ResponseApi<String> getTestAuthApi() {

        String one = "Message from Beers API";
        String result = beersFeignClient.getAll();
        return new ResponseApi<>(one + "Response from FeignController:  " + result);
    }
}
