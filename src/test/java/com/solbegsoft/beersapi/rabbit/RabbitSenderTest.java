package com.solbegsoft.beersapi.rabbit;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.solbegsoft.beersapi.models.dto.RootBeerDto;
import com.solbegsoft.beersapi.models.requests.RequestRootBeerDto;
import com.solbegsoft.beersapi.services.BeersService;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Test Rabbit Template
 */
class RabbitSenderTest extends AbstractRabbitTest{

    /**
     * @see RabbitTemplate
     */
    @MockBean
    private RabbitTemplate rabbitTemplate;

    /**
     * @see BeersService
     */
    @MockBean
    private BeersService beersService;

    /**
     * @see RabbitSender
     */
    @Autowired
    private RabbitSender rabbitSender;

    /**
     * @see Queue FavoritesAPI Output
     */
    @Autowired
    private Queue queueBeersApiOutput;

    /**
     * @see Queue BeersAPI input
     */
    @Autowired
    private Queue queueBeersApiError;

    /**
     * Test method {@link RabbitSender#sentToBeersOutput(String)}
     */
    @Test
    void testSentToBeersOutput_ShouldBeDoneOnce() {
        String sendString = "Information to send";
        rabbitSender.sentToBeersOutput(sendString);
        verify(rabbitTemplate, times(1)).convertAndSend(queueBeersApiOutput.getName(), sendString);
    }

    /**
     * Test method {@link RabbitSender#sendError(String)}
     */
    @Test
    void testSendError_ShouldBeDoneOnce() {
        String sendString = "Information about error";
        rabbitSender.sendError(sendString);
        verify(rabbitTemplate, times(1)).convertAndSend(queueBeersApiError.getName(), sendString);
    }

    /**
     * Test method {@link RabbitSender#receive(String)}
     */
    @Test
    void testReceive_WithEmptyRequest_ShouldBeDoneOnce() throws JsonProcessingException {
        RequestRootBeerDto request = RequestRootBeerDto.builder().build();
        String sendStringRequest = objectMapper.writeValueAsString(request);
        List<RootBeerDto> list = new ArrayList<>();
        String sendStringList = objectMapper.writeValueAsString(list);

        when(beersService.getBeers(request)).thenReturn(list);

        rabbitSender.receive(sendStringRequest);

        verify(rabbitTemplate, times(1)).convertAndSend(queueBeersApiOutput.getName(), sendStringList);
    }

    /**
     * Test method {@link RabbitSender#receive(String)}
     */
    @Test
    void testReceive_WithNonEmptyRequest_ShouldBeDoneOnce() throws JsonProcessingException {
        RequestRootBeerDto request = createRequestRootBeerDto("BlackBeer", "Food");
        String sendStringRequest = objectMapper.writeValueAsString(request);
        List<RootBeerDto> list = new ArrayList<>();
        String sendStringList = objectMapper.writeValueAsString(list);

        when(beersService.getBeers(request)).thenReturn(list);

        rabbitSender.receive(sendStringRequest);

        verify(rabbitTemplate, times(1)).convertAndSend(queueBeersApiOutput.getName(), sendStringList);
    }
}