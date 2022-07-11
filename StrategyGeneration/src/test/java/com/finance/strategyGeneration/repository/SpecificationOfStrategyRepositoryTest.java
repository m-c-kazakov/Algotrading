package com.finance.strategyGeneration.repository;

import com.finance.strategyGeneration.intagration.IntegrationTestBased;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.model.StatisticsOfStrategy;
import com.finance.strategyGeneration.model.creator.SpecificationOfStrategyCreator;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.RandomPopulationCreationManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Sql({
        "classpath:sql/data.sql"
})
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
        assertThat(repository.existsByHashCode("123123123")).isTrue();
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
        SpecificationOfStrategy specificationOfStrategy = randomPopulationCreationManager.execute();

        SpecificationOfStrategy entity = repository.save(SpecificationOfStrategyCreator.createWithHashCode(specificationOfStrategy));

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