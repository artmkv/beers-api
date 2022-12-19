package com.solbegsoft.beersapi.rabbit;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.solbegsoft.beersapi.models.requests.RequestRootBeerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Abstract Rabbit Test
 */
@AutoConfigureMockMvc
@SpringBootTest
public abstract class AbstractRabbitTest {

    /**
     * @see ObjectMapper
     */
    @Autowired
    protected ObjectMapper objectMapper;

    /**
     * Create RequestBeerDto
     *
     * @param beerName Beer name
     * @param foodName Food name
     * @return {@link RequestRootBeerDto}
     */
    protected RequestRootBeerDto createRequestRootBeerDto(String beerName, String foodName) {

        return RequestRootBeerDto.builder()
                .beerName(beerName)
                .foodName(foodName)
                .ebcLt(1.0)
                .ebcGt(3.0)
                .abvGt(4.0)
                .abvLt(5.0)
                .ibuGt(3.0)
                .ibuLt(1.0)
                .build();
    }
}
