package com.solbegsoft.beersapi.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.solbegsoft.beersapi.models.RootBeer;
import com.solbegsoft.beersapi.models.dto.RootBeerDto;
import com.solbegsoft.beersapi.models.requests.RequestRootBeerDto;
import com.solbegsoft.beersapi.models.response.ResponseApi;
import com.solbegsoft.beersapi.repositories.PunkApiRepository;
import com.solbegsoft.beersapi.utils.RootBeerMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Beers Controller Test
 */
class BeersControllerTest extends AbstractControllerTest {

    /**
     * @see PunkApiRepository
     */
    @MockBean
    private PunkApiRepository punkApiRepository;

    /**
     * @see RootBeerMapper
     */
    @Autowired
    private RootBeerMapper rootBeerMapper;

    /**
     * Name of general Endpoint
     */
    public static final String END_POINT_BEERS_API_V1_BEERS = "/beers-api/v1/beers";

    @Override
    protected String getEndPoint() {
        return END_POINT_BEERS_API_V1_BEERS;
    }

    /**
     * Test method {@link BeersController#findBeers(String, String, Double, Double, Double, Double, Double, Double)}
     * Should Return Empty Response
     *
     * @throws Exception exception
     */
    @Test
    void testFindBeers_WithoutRequest_ShouldReturnEmptyResponseWithStatusOk() throws Exception {

        mockMvc.perform(get(getEndPoint())
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isEmpty());
    }

    /**
     * Test method {@link BeersController#findBeers(String, String, Double, Double, Double, Double, Double, Double)}
     * With empty request should return response
     *
     * @throws Exception exception
     */
    @Test
    void testFindBeers_WithEmptyRequest_ShouldReturnResponseWithOneRootBeerWithStatusOk() throws Exception {

        RequestRootBeerDto request = new RequestRootBeerDto();
        List<RootBeer> beers = new ArrayList<>();
        beers.add(createRootBeer());
        List<RootBeerDto> expectedList = rootBeerMapper.mapListToDto(beers);

        when(punkApiRepository.getRootBeers(request)).thenReturn(beers);

        String actualString = mockMvc.perform(get(getEndPoint())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request))
                ).andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        checkEqualsExpectedListBeersAndActualString(expectedList, actualString);
    }

    /**
     * Test method {@link BeersController#findBeers(String, String, Double, Double, Double, Double, Double, Double)}
     * Should return response
     *
     * @throws Exception exception
     */
    @Test
    void testFindBeers_WithoutRequest_ShouldReturnResponseWithOneRootBeerWithStatusOk() throws Exception {

        RequestRootBeerDto request = new RequestRootBeerDto();
        List<RootBeer> beers = new ArrayList<>();
        beers.add(createRootBeer());
        List<RootBeerDto> expectedList = rootBeerMapper.mapListToDto(beers);

        when(punkApiRepository.getRootBeers(request)).thenReturn(beers);

        String actualString = mockMvc.perform(get(getEndPoint())
                ).andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        checkEqualsExpectedListBeersAndActualString(expectedList, actualString);
    }

    /**
     * Test method {@link BeersController#findBeers(String, String, Double, Double, Double, Double, Double, Double)}
     * Should return response
     *
     * @throws Exception exception
     */
    @Test
    void testFindBeers_WithNonEmptyRequest_ShouldReturnResponseWithOneRootBeerWithStatusOk() throws Exception {

        RequestRootBeerDto request = new RequestRootBeerDto();
        request.setEbcLt(10.0);
        List<RootBeer> beers = new ArrayList<>();
        beers.add(createRootBeer(2, "Beer"));
        beers.add(createRootBeer(12, "Zywec"));
        List<RootBeerDto> expectedList = rootBeerMapper.mapListToDto(beers);

        when(punkApiRepository.getRootBeers(request)).thenReturn(beers);

        String actualString = mockMvc.perform(get(getEndPoint())
                        .param("ebc_lt", "10")
                ).andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        checkEqualsExpectedListBeersAndActualString(expectedList, actualString);
    }

    /**
     * Check Equals Expected List Beers And Actual String
     *
     * @param expectedList List of RootBeer
     * @param actualString String Response
     * @throws JsonProcessingException exception
     */
    private void checkEqualsExpectedListBeersAndActualString(List<RootBeerDto> expectedList, String actualString) throws JsonProcessingException {
        TypeReference<ResponseApi<List<RootBeerDto>>> typeReference = new TypeReference<>() {
        };
        List<RootBeerDto> actualList = objectMapper.readValue(actualString, typeReference).getData();
        assertEquals(expectedList, actualList);
    }

    /**
     * Create RootBeer
     *
     * @return {@link RootBeer}
     */
    private RootBeer createRootBeer() {

        RootBeer beer = new RootBeer();
        beer.setId(5);
        beer.setName("Beer");
        beer.setTagline("German");
        beer.setFirstBrewed("10/2001");
        beer.setDescription("German beer");
        beer.setImageUrl("http://GermanBeer.png");
        beer.setAbv(4.2);
        beer.setIbu(8.1);
        beer.setTargetFg(1020.5);
        beer.setTargetOg(1060.8);
        beer.setEbc(7.4);
        beer.setSrm(4.6);
        beer.setPh(3.7);
        beer.setAttenuationLevel(88.8);
        beer.setFoodPairing(createFoodsList());
        beer.setBrewersTips("Something");
        beer.setContributedBy("Somebody");

        return beer;
    }

    /**
     * Create RootBeer with parameters
     *
     * @param id   integer Id
     * @param name String name
     * @return {@link RootBeer}
     */
    private RootBeer createRootBeer(Integer id, String name) {

        RootBeer beer = new RootBeer();
        beer.setId(id);
        beer.setName(name);
        beer.setTagline("German");
        beer.setFirstBrewed("2001-10-18");
        beer.setDescription("German beer");
        beer.setImageUrl("http://GermanBeer.png");
        beer.setAbv(4.2);
        beer.setIbu(8.1);
        beer.setTargetFg(1020.5);
        beer.setTargetOg(1060.8);
        beer.setEbc(7.4);
        beer.setSrm(4.6);
        beer.setPh(3.7);
        beer.setAttenuationLevel(88.8);
        beer.setFoodPairing(createFoodsList());
        beer.setBrewersTips("Something");
        beer.setContributedBy("Somebody");

        return beer;
    }

    /**
     * Create List of food
     *
     * @return {@link ArrayList} of food
     */
    private ArrayList<String> createFoodsList() {
        ArrayList<String> foods = new ArrayList<>();
        foods.add("Burger");
        foods.add("Fish");
        foods.add("Pizza");

        return foods;
    }
}