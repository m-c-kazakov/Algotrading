package com.finance.strategyGeneration.populationSelection;

import com.finance.dataHolder.DescriptionOfStrategy;

import java.util.List;

public interface PopulationSelection {
    List<DescriptionOfStrategy> execute(List<DescriptionOfStrategy> populationAfterMutation);
}
