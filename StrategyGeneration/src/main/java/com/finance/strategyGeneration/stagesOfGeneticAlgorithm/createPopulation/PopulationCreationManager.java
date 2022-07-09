package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation;

import com.finance.dataHolder.DescriptionOfStrategy;

import java.util.List;

public interface PopulationCreationManager {

    List<DescriptionOfStrategy>  execute();
}
