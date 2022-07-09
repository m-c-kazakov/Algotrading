package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation.exchangeManagers;

import com.finance.dataHolder.DescriptionOfStrategy;

import java.util.Set;
import java.util.stream.Stream;

public interface ExchangeManager {

    Stream<DescriptionOfStrategy> execute(Set<DescriptionOfStrategy> dataOfStrategies);
}
