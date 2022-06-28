package com.solbegsoft.beersapi.utils;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Getter
@Setter
@ConfigurationProperties(prefix = "punkapi")
@Configuration("punkapiProperties")
public class ApiProperties {

    private String url;
}
