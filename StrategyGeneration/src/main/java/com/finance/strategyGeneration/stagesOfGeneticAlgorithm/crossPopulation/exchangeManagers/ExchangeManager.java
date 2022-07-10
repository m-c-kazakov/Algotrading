package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation.exchangeManagers;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;

import java.util.Set;
import java.util.stream.Stream;

public interface ExchangeManager {

    Stream<SpecificationOfStrategy> execute(Set<SpecificationOfStrategy> dataOfStrategies);
}
