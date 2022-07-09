package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.strategiesForCreatingRandomPopulations.generatorRandomIndicators;

import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.indicators.Indicator;

public interface GeneratorOfRandomIndicators {
    Indicator getRandomIndicator(CurrencyPair currencyPair);
}
