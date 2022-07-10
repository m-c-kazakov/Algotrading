package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;

import java.util.List;

public interface PopulationCrossingManager {
    List<SpecificationOfStrategy> execute(List<SpecificationOfStrategy> population);
}
