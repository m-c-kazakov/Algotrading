package com.finance.strategyGeneration.repository;

import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyGeneration.intagration.IntegrationTestBased;
import com.finance.strategyGeneration.model.InformationOfIndicator;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.strategiesForCreatingRandomPopulations.generatorRandomIndicators.GeneratorOfRandomIndicators;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
class InformationOfIndicatorRepositoryTest extends IntegrationTestBased {

    @Autowired
    InformationOfIndicatorRepository repository;
    @Autowired
    GeneratorOfRandomIndicators generatorOfRandomIndicators;



    @Test
    void existsByHashCode() {
        InformationOfIndicator randomIndicator = generatorOfRandomIndicators.createRandomIndicator(CurrencyPair.EURUSD);
        assertThat(repository.existsByHashCode(randomIndicator.getHashCode())).isTrue();
    }

    @Test
    void findByHashCode() {
        InformationOfIndicator randomIndicator = generatorOfRandomIndicators.createRandomIndicator(CurrencyPair.EURUSD);
        assertThat(repository.findByHashCode(randomIndicator.getHashCode())).isPresent();
    }

    @Test
    void save() {
        InformationOfIndicator randomIndicator = generatorOfRandomIndicators.createRandomIndicator(CurrencyPair.EURUSD);
        assertThat(repository.save(randomIndicator)).isNotNull();
    }
}