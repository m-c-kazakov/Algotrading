package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation;

import com.finance.dataHolder.DescriptionOfStrategy;

import java.util.stream.Stream;

public interface Mutation {

    Stream<DescriptionOfStrategy> execute(DescriptionOfStrategy parentDescriptionOfStrategy);
}
