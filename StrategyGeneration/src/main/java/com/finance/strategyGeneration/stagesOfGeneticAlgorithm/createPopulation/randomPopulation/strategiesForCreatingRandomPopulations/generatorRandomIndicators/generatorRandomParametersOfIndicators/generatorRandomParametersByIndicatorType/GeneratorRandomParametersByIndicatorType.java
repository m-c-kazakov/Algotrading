package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.strategiesForCreatingRandomPopulations.generatorRandomIndicators.generatorRandomParametersOfIndicators.generatorRandomParametersByIndicatorType;

import java.util.Map;
import java.util.function.Supplier;


/**
 * Бин должен называться по имени в IndicatorType
 */
public interface GeneratorRandomParametersByIndicatorType {

    Map<String, String> get();

    Map<String, Supplier<String>> getParametersSupplierMap();
}
