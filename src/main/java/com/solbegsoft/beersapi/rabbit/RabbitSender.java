package com.solbegsoft.beersapi.rabbit;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solbegsoft.beersapi.models.dto.RootBeerDto;
import com.solbegsoft.beersapi.models.requests.RequestRootBeerDto;
import com.solbegsoft.beersapi.services.BeersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Rabbit Service
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitSender {

    /**
     * @see ObjectMapper
     */
    private final ObjectMapper objectMapper;

    /**
     * @see BeersService
     */
    private final BeersService beersService;

    /**
     * @see RabbitTemplate
     */
    private final RabbitTemplate rabbitTemplate;

    /**
     * @see Queue
     */
    private final Queue queueBeersApiOutput;

    /**
     * @see Queue
     */
    private final Queue queueBeersApiError;

    /**
     * Send Message to Favorites API
     *
     * @param toSend message to send
     */
    public void sentToBeersOutput(String toSend) {

        rabbitTemplate.convertAndSend(queueBeersApiOutput.getName(), toSend);
        log.info("[ASYNC] Response from BEERS-API to FAVORITES_API: SUCCESS");
    }

    /**
     * Send Error
     *
     * @param toSend String to Send
     */
    public void sendError(String toSend) {

        rabbitTemplate.convertAndSend(queueBeersApiError.getName(), toSend);
        log.info("[ASYNC] Response from BEERS-API: SUCCESS");
    }

    /**
     * Receive message
     *
     * @param string Input String
     */
    public void receive(String string) {

        log.info("[ASYNC] Request to BEERS-API: {}", string);
        String toSend;
        try {
            RequestRootBeerDto requestRootBeerDto = convertMessageToRequestRootBeerDto(string);
            List<RootBeerDto> beers = beersService.getBeers(requestRootBeerDto);
            toSend = objectMapper.writeValueAsString(beers);
        } catch (JsonProcessingException e) {
            throw new RabbitException(e.getMessage());
        }

        log.info("[ASYNC] Response from BEERS-API: {}", toSend);
        sentToBeersOutput(toSend);
    }

    /**
     * Converter Receive Message to Request Root Beer Dto
     *
     * @param message String message
     * @return {@link RequestRootBeerDto}
     */
    private RequestRootBeerDto convertMessageToRequestRootBeerDto(String message) {
        try {
            return objectMapper.readValue(message, RequestRootBeerDto.class);
        } catch (JsonProcessingException e) {
            throw new RabbitException(e.getMessage());
        }
    }
}