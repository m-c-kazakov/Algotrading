package com.finance.strategyGeneration.populationSelection;

import com.finance.dataHolder.DescriptionOfStrategy;

import java.util.List;
import java.util.Set;

public interface СheckingTheUniquenessOfStrategies {
    Set<DescriptionOfStrategy> execute(List<DescriptionOfStrategy> populationAfterMutation);
}
