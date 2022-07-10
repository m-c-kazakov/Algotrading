package com.finance.strategyGeneration.service;

import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyGeneration.intagration.IntegrationTestBased;
import com.finance.strategyGeneration.model.InformationOfIndicator;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.strategiesForCreatingRandomPopulations.generatorRandomIndicators.GeneratorOfRandomIndicators;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
class InformationOfIndicatorServiceImplTest extends IntegrationTestBased {

    @Autowired
    InformationOfIndicatorService informationOfIndicatorService;
    @Autowired
    GeneratorOfRandomIndicators generatorOfRandomIndicators;

    @Test
    void create() {
        InformationOfIndicator indicator = generatorOfRandomIndicators.createRandomIndicator(CurrencyPair.EURUSD);

        informationOfIndicatorService.create(indicator);
    }
}