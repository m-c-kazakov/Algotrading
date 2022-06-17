package com.finance.strategyGeneration.populationSelection;

import com.finance.dataHolder.DataOfStrategy;

import java.util.List;

public interface PopulationSelection {
    List<DataOfStrategy> execute(List<DataOfStrategy> populationAfterMutation);
}
