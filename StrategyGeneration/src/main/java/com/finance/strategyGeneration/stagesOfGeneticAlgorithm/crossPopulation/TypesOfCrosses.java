package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation;

import com.finance.strategyDescriptionParameters.indicators.Indicator;

import java.util.List;

public interface TypesOfCrosses {

    List<Indicator> singlePointCrossing(List<Indicator> first, List<Indicator> second, int separator);
}
