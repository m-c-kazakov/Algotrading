package com.finance.strategyGeneration.repository;

import com.finance.strategyGeneration.intagration.IntegrationTestBased;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class SpecificationOfStrategyRepositoryTest extends IntegrationTestBased {

    public static final long SPECIFICATION_ID = 1L;
    @Autowired
    SpecificationOfStrategyRepository repository;

    @Test
    void existsByHashCode() {
        assertThat(repository.existsByHashCode(123123123)).isTrue();
    }

    @Test
    void updateStatisticsOfStrategyId() {
        long statisticsOfStrategyId = 5L;
        repository.updateStatisticsOfStrategyId(SPECIFICATION_ID, statisticsOfStrategyId);
        Optional<SpecificationOfStrategy> specification = repository.findById(SPECIFICATION_ID);
        assertThat(specification).isPresent();
        specification.ifPresent(specificationOfStrategy ->
                assertThat(specificationOfStrategy.getStatisticsOfStrategyId()).isEqualTo(statisticsOfStrategyId)
        );

    }

    @Test
    void findTheBestStrategy() {
        assertThat(repository.findTheBestStrategy(1)).hasSize(1);
    }
}