package com.finance.strategyGeneration;

import com.finance.strategyGeneration.dataHolder.DataOfStrategy;

import java.util.List;

public interface PopulationCrossingManager {
    List<DataOfStrategy> execute(List<DataOfStrategy> population);
}
