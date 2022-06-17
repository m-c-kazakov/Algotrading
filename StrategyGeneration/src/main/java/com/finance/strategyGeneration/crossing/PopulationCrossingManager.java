package com.finance.strategyGeneration.crossing;

import com.finance.dataHolder.DataOfStrategy;

import java.util.List;

public interface PopulationCrossingManager {
    List<DataOfStrategy> execute(List<DataOfStrategy> population);
}
