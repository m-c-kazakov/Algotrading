package com.finance.strategyGeneration.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@TestConfiguration
public class TestConfig {


    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
