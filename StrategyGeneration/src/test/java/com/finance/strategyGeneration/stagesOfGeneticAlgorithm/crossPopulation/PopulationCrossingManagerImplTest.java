package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation;

import com.finance.strategyGeneration.intagration.IntegrationTestBased;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.RandomPopulationCreationManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PopulationCrossingManagerImplTest extends IntegrationTestBased {

    @Autowired
    PopulationCrossingManagerImpl populationCrossingManager;
    @Autowired
    RandomPopulationCreationManager randomPopulationCreationManager;

    @Test
    void execute() {

        assertThat(populationCrossingManager.execute(List.of(randomPopulationCreationManager.execute(),
                randomPopulationCreationManager.execute()))).isNotEmpty();
    }
}