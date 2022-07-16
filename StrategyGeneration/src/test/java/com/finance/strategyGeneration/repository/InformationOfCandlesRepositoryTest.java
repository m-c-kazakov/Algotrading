package com.finance.strategyGeneration.repository;

import com.finance.strategyGeneration.intagration.IntegrationTestBased;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.model.storage.InformationOfCandlesStorage;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.RandomPopulationCreationManager;
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
        InformationOfCandlesStorage informationOfCandles = execute.getInformationOfCandles();
        assertThat(repository.existsByHashCode(informationOfCandles.getInformationOfCandlesHashCode())).isTrue();
    }

    @Test
    void findByHashCode() {
        SpecificationOfStrategy execute = randomPopulationCreationManager.execute();
        InformationOfCandlesStorage informationOfCandles = execute.getInformationOfCandles();
        assertThat(repository.findByHashCode(informationOfCandles.getInformationOfCandlesHashCode())).isPresent();
    }
}