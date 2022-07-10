package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation;

import com.finance.strategyGeneration.model.InformationOfIndicator;

import java.util.List;

public interface TypesOfCrosses {

    List<InformationOfIndicator> singlePointCrossing(List<InformationOfIndicator> first, List<InformationOfIndicator> second, int separator);
}
