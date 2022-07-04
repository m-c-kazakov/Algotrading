package com.finance.strategyGeneration.crossing;

import com.finance.dataHolder.DescriptionOfStrategy;

import java.util.List;

public interface PopulationCrossingManager {
    List<DescriptionOfStrategy> execute(List<DescriptionOfStrategy> population);
}
