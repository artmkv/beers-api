package com.solbegsoft.beersapi.configurations;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Rabbit Configuration
 */
@Configuration
public class RabbitConfig {

    public static final String QUEUE_NAME_GET_ALL_BEERS = "beers-api.queue.get-all.input";
    public static final String QUEUE_NAME_FAVORITES = "favorites.queue.get-all.input";
    public static final String QUEUE_NAME_ERROR = "beers.queue.get-all.input";

    @Bean
    public Queue queueBeers() {
        return new Queue(QUEUE_NAME_GET_ALL_BEERS);
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