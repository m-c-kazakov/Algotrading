package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation;

import com.finance.dataHolder.DescriptionOfStrategy;

import java.util.List;

public interface PopulationCrossingManager {
    List<DescriptionOfStrategy> execute(List<DescriptionOfStrategy> population);
}
