package com.solbegsoft.beersapi.configurations;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Async Configuration
 */
@Configuration
public class AsyncConfig {

    public static final String QUEUE_NAME_BEERS = "beers";
    public static final String QUEUE_NAME_FAVORITES = "favorites";
    public static final String QUEUE_NAME_ERROR = "error";

    @Bean
    public Queue queueBeers() {
        return new Queue(QUEUE_NAME_BEERS);
    }

    @Bean
    public Queue queueFavorites() {
        return new Queue(QUEUE_NAME_FAVORITES);
    }

    @Bean
    public Queue queueError() {
        return new Queue(QUEUE_NAME_ERROR);
    }
}