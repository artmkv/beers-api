package com.solbegsoft.beersapi.rabbit;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.solbegsoft.beersapi.models.requests.RequestRootBeerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test {@link RabbitReceiver}
 */
class RabbitReceiverTest extends AbstractRabbitTest{

    /**
     * @see RabbitReceiver
     */
    @Autowired
    private RabbitReceiver rabbitReceiver;

    /**
     * @see RabbitSender
     */
    @MockBean
    private RabbitSender rabbitSender;

    /**
     * Test {@link RabbitReceiverTest#rabbitReceiver} with empty Request
     */
    @Test
    void testReceiveGetAllBeers_WithEmptyRequest_ShouldBeDoneOnce() throws JsonProcessingException {
        RequestRootBeerDto request = RequestRootBeerDto.builder().build();
        String sendStringRequest = objectMapper.writeValueAsString(request);

        rabbitReceiver.receiveGetAllBeers(sendStringRequest);
        verify(rabbitSender, times(1)).receive(sendStringRequest);
    }

    /**
     * Test method {@link RabbitReceiverTest#rabbitReceiver} with full fields of Request
     */
    @Test
    void testReceiveGetAllBeers_WithNonEmptyRequest_ShouldBeDoneOnce() throws JsonProcessingException {
        RequestRootBeerDto request = createRequestRootBeerDto("Beer", "Pizza");
        String sendStringRequest = objectMapper.writeValueAsString(request);

        rabbitReceiver.receiveGetAllBeers(sendStringRequest);
        verify(rabbitSender, times(1)).receive(sendStringRequest);
    }
}