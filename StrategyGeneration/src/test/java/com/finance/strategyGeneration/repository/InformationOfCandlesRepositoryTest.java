package com.finance.strategyGeneration.repository;

import com.finance.strategyGeneration.intagration.IntegrationTestBased;
import com.finance.strategyGeneration.model.InformationOfCandles;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.RandomPopulationCreationManager;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@Transactional
class InformationOfCandlesRepositoryTest extends IntegrationTestBased {

    @Autowired
    InformationOfCandlesRepository repository;

    @Autowired
    RandomPopulationCreationManager randomPopulationCreationManager;

    @Test
    void existsByHashCode() {
        SpecificationOfStrategy execute = randomPopulationCreationManager.execute();
        InformationOfCandles informationOfCandles = execute.receiveInformationOfCandles();
        assertThat(repository.existsByHashCode(informationOfCandles.getHashCode())).isTrue();
    }

    @Test
    @Disabled
    void findByHashCode() {
        SpecificationOfStrategy execute = randomPopulationCreationManager.execute();
        InformationOfCandles informationOfCandles = execute.receiveInformationOfCandles();
        assertThat(repository.findByHashCode(informationOfCandles.getHashCode())).isPresent();
    }
}