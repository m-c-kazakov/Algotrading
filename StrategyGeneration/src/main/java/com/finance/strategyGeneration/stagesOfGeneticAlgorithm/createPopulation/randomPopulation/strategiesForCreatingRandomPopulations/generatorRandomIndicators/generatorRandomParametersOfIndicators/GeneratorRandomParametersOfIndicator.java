package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.strategiesForCreatingRandomPopulations.generatorRandomIndicators.generatorRandomParametersOfIndicators;

import com.finance.strategyDescriptionParameters.indicators.IndicatorType;

import java.util.Map;

public interface GeneratorRandomParametersOfIndicator {
    Map<String, String> getRandomParametersByIndicatorType(IndicatorType indicatorType);
}
