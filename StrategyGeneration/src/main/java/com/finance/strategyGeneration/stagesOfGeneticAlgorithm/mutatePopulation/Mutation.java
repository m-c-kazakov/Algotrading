package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;

import java.util.stream.Stream;

public interface Mutation {

    Stream<SpecificationOfStrategy> execute(SpecificationOfStrategy parentSpecificationOfStrategy);
}
