package com.finance.strategyGeneration.stagesOfGeneticAlgorithm;

import com.finance.dataHolder.DescriptionOfStrategy;

import java.util.Set;

public interface GeneticAlgorithm {
    Set<DescriptionOfStrategy> execute();
}
