package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation;

import com.finance.strategyGeneration.intagration.IntegrationTestBased;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class RandomPopulationCreationManagerImplTest extends IntegrationTestBased {
    @Autowired
    RandomPopulationCreationManager randomPopulationCreationManager;

    @Test
    void execute() {
        assertThat(randomPopulationCreationManager.execute()).isNotNull();
    }
}