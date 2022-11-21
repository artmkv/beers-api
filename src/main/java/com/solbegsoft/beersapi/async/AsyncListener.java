package com.solbegsoft.beersapi.async;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.solbegsoft.beersapi.configurations.AsyncConfig.QUEUE_NAME_BEERS;

/**
 * Async Listener
 */
@Slf4j
@RabbitListener(queues = QUEUE_NAME_BEERS)
@Component
@RequiredArgsConstructor
public class AsyncListener {

    /**
     * @see AsyncService
     */
    private final AsyncService asyncService;

    /**
     * Rabbit Handler
     *
     * @param message string
     */
    @RabbitHandler
    public void receiveMessage(String message) {

        log.info("[ASYNC] - " + "Received  BEER <" + message + ">");
        asyncService.receive(message);
        log.info("[ASYNC] MESSAGE Received SUCCESS");
    }
}