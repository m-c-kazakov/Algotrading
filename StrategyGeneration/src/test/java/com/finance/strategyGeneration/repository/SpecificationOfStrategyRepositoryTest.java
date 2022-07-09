package com.finance.strategyGeneration.repository;

import com.finance.strategyGeneration.intagration.IntegrationTestBased;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
class SpecificationOfStrategyRepositoryTest extends IntegrationTestBased {

    @Autowired
    SpecificationOfStrategyRepository repository;
    @Autowired
    StatisticsOfStrategyRepository statisticsOfStrategyRepository;

    @Test
    void existsByHashCode() {
        assertThat(repository.existsByHashCode(123123123)).isTrue();
    }

    @Test
    void updateStatisticsOfStrategyId() {
        long statisticsOfStrategyId = 5L;
        Optional<SpecificationOfStrategy> optionalSpecificationOfStrategy = repository.findAll().stream().findFirst();
        assertThat(optionalSpecificationOfStrategy).isPresent();
        optionalSpecificationOfStrategy.ifPresent(specificationOfStrategy -> {

            repository.updateStatisticsOfStrategyId(specificationOfStrategy.getId(), statisticsOfStrategyId);
            Optional<SpecificationOfStrategy> optionalSpecification = repository.findById(
                    specificationOfStrategy.getId());

            assertThat(optionalSpecification).isPresent();
            optionalSpecification.ifPresent(specification ->
                    assertThat(specification.getStatisticsOfStrategyId()).isEqualTo(statisticsOfStrategyId)
            );
        });
    }

    @Test
    void findTheBestStrategy() {
        assertThat(statisticsOfStrategyRepository.findAll()).isNotEmpty();
        assertThat(repository.findAll()).isNotEmpty();
        assertThat(repository.findTheBestStrategy(1))
                .hasSize(1);
    }
}