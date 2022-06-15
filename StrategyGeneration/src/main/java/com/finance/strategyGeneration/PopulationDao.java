package com.finance.strategyGeneration;

import com.finance.strategyGeneration.dataHolder.DataOfStrategy;

import java.util.List;

public interface PopulationDao {
    List<DataOfStrategy> findTheBestIndividual(int numberOfIndividuals);
}
