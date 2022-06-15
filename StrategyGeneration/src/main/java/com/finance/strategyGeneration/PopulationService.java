package com.finance.strategyGeneration;

import com.finance.strategyGeneration.dataHolder.DataOfStrategy;

import java.util.List;

public interface PopulationService {
    List<DataOfStrategy> findTheBestIndividual(int numberOfIndividuals);
}
