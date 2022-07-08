package com.finance.strategyGeneration.controller;

import com.finance.dataHolder.StatisticsDataOfStrategy;
import com.finance.strategyGeneration.intagration.IntegrationTestBased;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class GeneticAlgorithmControllerTest extends IntegrationTestBased {

    MockMvc mockMvc;
    ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    void saveStatisticsDataOfStrategy() {

        StatisticsDataOfStrategy request = StatisticsDataOfStrategy.builder()
                .specificationOfStrategyId(1L)
                .score(1000L)
                .valueOfAcceptableRisk(15)
                .maximumPercentDrawdownFromStartScore(10)
                .averagePercentDrawdownOfScore(5)
                .maximumValueFromScore(2000)
                .numberOfWinningDeals(20)
                .numberOfLosingDeals(15)
                .build();

//        mockMvc.perform(post("/strategyGeneration/statisticsDataOfStrategy")
//                        .contentType(ContentType.APPLICATION_JSON.getMimeType())
//                        .content(objectMapper.writeValueAsString(request)))
//                .andExpectAll(
//                        status().is2xxSuccessful()
//                );
    }
}