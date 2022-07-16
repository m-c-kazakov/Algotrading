package com.finance.strategyGeneration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.strategyGeneration.intagration.IntegrationTestBased;
import com.finance.strategyGeneration.repository.StatisticsOfStrategyRepository;
import com.finance.utils.StatisticsDataOfStrategyDto;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class GeneticAlgorithmControllerTest extends IntegrationTestBased {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    StatisticsOfStrategyRepository repository;


    @Test
    void saveStatisticsDataOfStrategy() {

        try {
            long specificationOfStrategyId = 1L;
            StatisticsDataOfStrategyDto request = StatisticsDataOfStrategyDto.builder()
                    .specificationOfStrategyId(specificationOfStrategyId)
                    .score(1000L)
                    .valueOfAcceptableRisk(15)
                    .maximumPercentDrawdownFromStartScore(10L)
                    .averagePercentDrawdownOfScore(5L)
                    .maximumValueFromScore(2000L)
                    .numberOfWinningDeals(20)
                    .numberOfLosingDeals(15)
                    .build();

            String json = objectMapper.writeValueAsString(request);
            mockMvc.perform(post("/strategyGeneration/statisticsDataOfStrategy")
                            .contentType(ContentType.APPLICATION_JSON.getMimeType())
                            .content(json))
                    .andExpectAll(
                            status().is2xxSuccessful()
                    );

            assertThat(repository.findAll())
                    .filteredOn(
                            statisticsOfStrategy -> Objects.equals(statisticsOfStrategy.getSpecificationOfStrategyId(),
                                    specificationOfStrategyId))
                    .isNotEmpty();


        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}