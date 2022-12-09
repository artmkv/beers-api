package com.solbegsoft.beersapi.configurations;


import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Rabbit Configuration
 */
@Configuration
public class RabbitConfig {

    /**
     * Queue name input messages
     */
    @Value("${rabbitmq.queue.input}")
    public static final String QUEUE_INPUT_GET_ALL_BEERS_API = "beers-api.queue.get-all.input";

    /**
     * Queue name for output messages
     */
    @Value("${rabbitmq.queue.output}")
    public static final String QUEUE_OUTPUT_GET_ALL_BEERS_API = "beers-api.queue.get-all.output";

    /**
     * Queue name for Error
     */
    @Value("${rabbitmq.queue.error}")
    public static final String QUEUE_BEERS_API_ERROR = "beers-api.queue.get-all.error";

    /**
     * Queue name for output messages from Favorites API
     */
    public static final String QUEUE_OUTPUT_GET_ALL_FAVORITES_API = "favorites-api.queue.get-all.output";

    @Bean
    public Queue queueBeersApiInput() {
        return new Queue(QUEUE_INPUT_GET_ALL_BEERS_API);
    }

    @Bean
    public Queue queueBeersApiOutput() {
        return new Queue(QUEUE_OUTPUT_GET_ALL_BEERS_API);
    }

    @Bean
    public Queue queueBeersApiError() {
        return new Queue(QUEUE_BEERS_API_ERROR);
    }
}