package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.selectionPopulation;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;

import java.util.List;

public interface СheckingTheUniquenessOfStrategies {
    List<SpecificationOfStrategy> execute(List<SpecificationOfStrategy> populationAfterMutation);
}
