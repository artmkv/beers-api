package com.solbegsoft.beersapi.async;


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
 * Async Service
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AsyncService {

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
    private final RabbitTemplate template;

    /**
     * @see Queue
     */
    private final Queue queueFavorites;

    /**
     * @see Queue
     */
    private final Queue queueError;

    /**
     * Send Message to Favorites API
     *
     * @param toSend message to send
     */
    public void sentToFavorites(String toSend) {

        template.convertAndSend(queueFavorites.getName(), toSend);

        log.info("[ASYNC] Response from BEER: SUCCESS");
    }

    public void sendError(String toSend) {

        template.convertAndSend(queueError.getName(), toSend);

        log.info("[ASYNC] Response from BEER: SUCCESS");
    }

    public void receive(String string) {

        log.info("[ASYNC] Request to BEER: " + string);
        String toSend;
        RequestRootBeerDto requestRootBeerDto = convertMessageToRequest(string);
        List<RootBeerDto> beers = beersService.getBeers(requestRootBeerDto);

        try {
            toSend = objectMapper.writeValueAsString(beers);
        } catch (JsonProcessingException e) {
            throw new AsyncException(e.getMessage());
        }

        log.info("[ASYNC] Response from BEER: " + toSend);
        sentToFavorites(toSend);
    }

    private RequestRootBeerDto convertMessageToRequest(String message) {
        try {
            return objectMapper.readValue(message, RequestRootBeerDto.class);
        } catch (JsonProcessingException e) {
            throw new AsyncException(e.getMessage());
        }
    }
}