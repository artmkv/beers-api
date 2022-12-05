package com.solbegsoft.beersapi.rabbit;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.solbegsoft.beersapi.configurations.RabbitConfig.QUEUE_NAME_GET_ALL_BEERS;

/**
 * Async Listener
 */
@Slf4j
@RabbitListener(queues = QUEUE_NAME_GET_ALL_BEERS)
@Component
@RequiredArgsConstructor
public class RabbitReceiver {

    /**
     * @see RabbitSender
     */
    private final RabbitSender rabbitSender;

    /**
     * Rabbit Handler
     *
     * @param message string
     */
    @RabbitHandler
    public void receiveGetAllBeers(String message) {

        log.info("[ASYNC] - Received  BEER <{}>", message);
        rabbitSender.receive(message);
        log.info("[ASYNC] MESSAGE Received SUCCESS");
    }
}