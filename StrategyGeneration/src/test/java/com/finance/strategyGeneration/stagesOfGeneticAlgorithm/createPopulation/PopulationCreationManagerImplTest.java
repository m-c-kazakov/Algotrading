package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation;

import com.finance.strategyGeneration.intagration.IntegrationTestBased;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
class PopulationCreationManagerImplTest extends IntegrationTestBased {

    @Autowired
    PopulationCreationManagerImpl populationCreationManager;

    @Test
    void execute() {
        assertThat(populationCreationManager.execute()).isNotEmpty();
    }
}