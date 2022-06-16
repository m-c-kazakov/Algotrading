package com.finance.strategyGeneration.crossing;

import com.finance.strategyGeneration.dataHolder.DataOfStrategy;

import java.util.List;

public interface PopulationCrossingManager {
    List<DataOfStrategy> execute(List<DataOfStrategy> population);
}
