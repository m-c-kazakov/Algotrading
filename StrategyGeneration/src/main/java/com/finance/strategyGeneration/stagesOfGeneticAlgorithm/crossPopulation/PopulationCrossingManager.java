package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;

import java.util.List;

public interface PopulationCrossingManager {
    void execute(List<SpecificationOfStrategy> population);
}
