package com.solbegsoft.beersapi.rabbit;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.solbegsoft.beersapi.configurations.RabbitConfig.QUEUE_INPUT_GET_ALL_BEERS_API;
import static com.solbegsoft.beersapi.configurations.RabbitConfig.QUEUE_OUTPUT_GET_ALL_FAVORITES_API;

/**
 * Rabbit Listener
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitReceiver {

    /**
     * @see RabbitSender
     */
    private final RabbitSender rabbitSender;

    /**
     * Rabbit handler BeersInput
     *
     * @param message string
     */
    @RabbitListener(queues = QUEUE_INPUT_GET_ALL_BEERS_API)
    public void receiveGetAllBeers(String message) {

        log.info("[ASYNC] Received from BeersInput <{}>", message);
        rabbitSender.receive(message);
        log.info("[ASYNC] MESSAGE Received SUCCESS");
    }

    /**
     * Rabbit handler from FavoritesOutput
     *
     * @param message string
     */
    @RabbitListener(queues = QUEUE_OUTPUT_GET_ALL_FAVORITES_API)
    public void receiveFromFavoritesOutput(String message){
        log.info("[ASYNC] Received From FavoritesOutput <{}>", message);
    }
}