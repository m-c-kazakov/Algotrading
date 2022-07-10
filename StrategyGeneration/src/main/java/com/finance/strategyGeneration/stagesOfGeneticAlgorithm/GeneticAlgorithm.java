package com.finance.strategyGeneration.stagesOfGeneticAlgorithm;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;

import java.util.List;

public interface GeneticAlgorithm {
    List<SpecificationOfStrategy> execute();
}
