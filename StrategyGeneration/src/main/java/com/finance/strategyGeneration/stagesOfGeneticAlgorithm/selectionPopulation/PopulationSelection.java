package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.selectionPopulation;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;

import java.util.List;

public interface PopulationSelection {
    List<SpecificationOfStrategy> execute(List<SpecificationOfStrategy> populationAfterMutation);

    Boolean isValidStrategy(SpecificationOfStrategy specificationOfStrategy);
}
