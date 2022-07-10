package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;

import java.util.List;

public interface MutationOfIndividual {
    List<SpecificationOfStrategy> execute(List<SpecificationOfStrategy> populationAfterCrossing);
}
