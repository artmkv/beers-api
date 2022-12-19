package com.solbegsoft.beersapi.repositories;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *  Beers Feign Client
 */
@FeignClient(name = "${feign.name}")
public interface BeersFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "${feign.endpoint-all}")
    String getAll();

}