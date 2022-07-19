package com.finance.strategyGeneration.repository;

import com.finance.strategyGeneration.intagration.IntegrationTestBased;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.model.StatisticsOfStrategy;
import com.finance.strategyGeneration.model.creator.SpecificationOfStrategyCreator;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.RandomPopulationCreationManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@Transactional
class SpecificationOfStrategyRepositoryTest extends IntegrationTestBased {

    @Autowired
    SpecificationOfStrategyRepository repository;
    @Autowired
    StatisticsOfStrategyRepository statisticsOfStrategyRepository;
    @Autowired
    RandomPopulationCreationManager randomPopulationCreationManager;

    @Test
    void existsByHashCode() {
        SpecificationOfStrategy entity = repository.save(randomPopulationCreationManager.execute());
        assertThat(repository.existsByHashCode(entity.getHashCode())).isTrue();
    }

    @Test
    void updateStatisticsOfStrategyId() {
        SpecificationOfStrategy entity = repository.save(randomPopulationCreationManager.execute());

        long statisticsOfStrategyId = 5L;

        repository.updateStatisticsOfStrategyId(entity.getId(), statisticsOfStrategyId);
        Optional<SpecificationOfStrategy> optionalSpecification = repository.findById(
                entity.getId());

        assertThat(optionalSpecification).isPresent();
        optionalSpecification.ifPresent(specification ->
                assertThat(specification.getStatisticsOfStrategyId()).isEqualTo(statisticsOfStrategyId)
        );
    }

    @Test
    void findTheBestStrategy() {
        SpecificationOfStrategy specificationOfStrategy = randomPopulationCreationManager.execute();

        SpecificationOfStrategy entity = repository.save(
                SpecificationOfStrategyCreator.createWithHashCodeAndDataOfCreation(specificationOfStrategy));

        StatisticsOfStrategy statisticsOfStrategy = StatisticsOfStrategy
                .builder()
                .specificationOfStrategyId(entity.getId())
                .score(123)
                .valueOfAcceptableRisk(1)
                .maximumPercentDrawdownFromStartScore(4)
                .averagePercentDrawdownOfScore(3)
                .maximumValueFromScore(4)
                .numberOfWinningDeals(4)
                .numberOfLosingDeals(4)
                .build();

        statisticsOfStrategyRepository.save(statisticsOfStrategy);

        assertThat(repository.findTheBestStrategy(1))
                .isNotEmpty();
    }
}