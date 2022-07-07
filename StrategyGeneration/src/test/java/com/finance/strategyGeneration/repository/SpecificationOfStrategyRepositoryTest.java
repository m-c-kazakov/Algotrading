package com.finance.strategyGeneration.repository;

import com.finance.strategyGeneration.intagration.IntegrationTestBased;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class SpecificationOfStrategyRepositoryTest extends IntegrationTestBased {

    @Autowired
    SpecificationOfStrategyRepository specificationOfStrategyRepository;

    @Test
    void existsByHashCode() {

        assertThat(specificationOfStrategyRepository.existsByHashCode(123123123)).isTrue();
    }

    @Test
    void updateStatisticsOfStrategyId() {
    }

    @Test
    void findTheBestStrategy() {
        specificationOfStrategyRepository.findAll();
//        specificationOfStrategyRepository.findTheBestStrategy(1);
    }
}