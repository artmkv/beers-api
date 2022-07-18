package com.solbegsoft.beersapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class BeersApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeersApiApplication.class, args);
    }

}