package com.solbegsoft.beersapi.controllers;


import org.hamcrest.Matchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test to Validate Requests in {@link BeersController}
 */
class ValidateRequestBeerControllerTest extends BeersControllerTest {

    /**
     * Max of range to parameter double type
     */
    public static final String OUT_OF_RANGE_DOUBLE_PARAMETER = "100";

    /**
     * Min of range to parameter double type
     */
    public static final String MIN_DOUBLE_PARAMETER = "0";

    /**
     * Test to check if parameter beerName is any string
     *
     * @param param parameter beerName
     * @throws Exception exception
     */
    @ParameterizedTest
    @ValueSource(strings = {"name", " - "})
    @EmptySource
    @NullSource
    void validateRequest_WhenBeerNameIsAnything_ShouldReturnBadRequestAndMessage(String param) throws Exception {

        mockMvc.perform(get(getEndPoint())
                        .param("beerName", param)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isEmpty());
    }

    /**
     * Test to check if parameter foodName is any string
     *
     * @param param parameter foodName
     * @throws Exception exception
     */
    @ParameterizedTest
    @ValueSource(strings = {"name", " - "})
    @EmptySource
    @NullSource
    void validateRequest_WhenFoodNameIsAnything_ShouldReturnBadRequestAndMessage(String param) throws Exception {

        mockMvc.perform(get(getEndPoint())
                        .param("foodName", param)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isEmpty());
    }

    /**
     * Test to check if parameters double type are out of range
     *
     * @param param parameter double type
     * @throws Exception exception
     */
    @ParameterizedTest
    @ValueSource(strings = {"abv_gt", "abv_lt", "ibu_gt", "ibu_lt", "ebc_gt", "ebc_lt"})
    void validateRequest_WhenParamsOutOfRange_ShouldReturnBadRequestAndMessage(String param) throws Exception {

        mockMvc.perform(get(getEndPoint())
                        .param(param, OUT_OF_RANGE_DOUBLE_PARAMETER)
                ).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.data").value(Matchers.stringContainsInOrder("findBeers")))
                .andExpect(jsonPath("$.statusCode").value("400"))
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value(Matchers.stringContainsInOrder("Invalid value in parameter")));
    }

    /**
     * Test to check if parameters double type are average number of range
     *
     * @param param parameter double type
     * @throws Exception exception
     */
    @ParameterizedTest
    @ValueSource(strings = {"abv_gt", "abv_lt", "ibu_gt", "ibu_lt", "ebc_gt", "ebc_lt"})
    void validateRequest_WhenParamsCorrect_ShouldReturnBadRequestAndEmptyData(String param) throws Exception {

        mockMvc.perform(get(getEndPoint())
                        .param(param, "12")
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isEmpty());
    }

    /**
     * Test to check if parameters double type are min number of range
     *
     * @param param parameter double type
     * @throws Exception exception
     */
    @ParameterizedTest
    @ValueSource(strings = {"abv_gt", "abv_lt", "ibu_gt", "ibu_lt", "ebc_gt", "ebc_lt"})
    void validateRequest_WhenParameterIsZero_ShouldReturnBadRequestAndEmptyData(String param) throws Exception {

        mockMvc.perform(get(getEndPoint())
                        .param(param, MIN_DOUBLE_PARAMETER)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isEmpty());
    }

    /**
     * Test to check if parameters double type aren't number
     *
     * @param param parameter double type
     * @throws Exception exception
     */
    @ParameterizedTest
    @ValueSource(strings = {"abv_gt", "abv_lt", "ibu_gt", "ibu_lt", "ebc_gt", "ebc_lt"})
    void validateRequest_WhenDoubleParametersIsString_ShouldReturnBadRequestAndEmptyData(String param) throws Exception {

        mockMvc.perform(get(getEndPoint())
                        .param(param, "Something")
                ).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.data").value(Matchers.stringContainsInOrder("Failed to convert value of type")));
    }

    /**
     * Test to check if parameters double type are empty
     *
     * @param param parameter double type
     * @throws Exception exception
     */
    @ParameterizedTest
    @ValueSource(strings = {"abv_gt", "abv_lt", "ibu_gt", "ibu_lt", "ebc_gt", "ebc_lt"})
    void validateRequest_WhenDoubleParametersIsEmpty_ShouldReturnBadRequestAndEmptyData(String param) throws Exception {

        mockMvc.perform(get(getEndPoint())
                        .param(param, "")
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isEmpty());
    }
}