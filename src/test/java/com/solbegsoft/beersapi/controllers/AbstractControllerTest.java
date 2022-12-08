package com.solbegsoft.beersapi.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Abstract class for Controller Test
 */
public abstract class AbstractControllerTest extends AbstractMVCTest {

    /**
     * @see ObjectMapper
     */
    @Autowired
    protected ObjectMapper objectMapper;

    /**
     * Get End Point
     *
     * @return String
     */
    protected abstract String getEndPoint();
}