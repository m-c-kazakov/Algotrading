package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.selectionPopulation;

import com.finance.dataHolder.DescriptionOfStrategy;

import java.util.List;

public interface PopulationSelection {
    List<DescriptionOfStrategy> execute(List<DescriptionOfStrategy> populationAfterMutation);
}
