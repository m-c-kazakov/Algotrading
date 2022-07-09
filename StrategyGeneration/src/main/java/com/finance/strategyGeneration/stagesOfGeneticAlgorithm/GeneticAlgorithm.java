package com.finance.strategyGeneration.stagesOfGeneticAlgorithm;

import com.finance.dataHolder.DescriptionOfStrategy;

import java.util.List;

public interface GeneticAlgorithm {
    List<DescriptionOfStrategy> execute();
}
