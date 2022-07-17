package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;

import java.util.List;
import java.util.stream.Stream;

public interface MutationOfIndividual {
    Stream<SpecificationOfStrategy> execute(SpecificationOfStrategy specificationOfStrategy);

    void execute(List<SpecificationOfStrategy> populationAfterCrossing);
}
