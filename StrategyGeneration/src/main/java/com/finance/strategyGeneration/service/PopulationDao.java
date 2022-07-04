package com.finance.strategyGeneration.service;

import com.finance.dataHolder.DescriptionOfStrategy;

import java.util.List;

public interface PopulationDao {
    List<DescriptionOfStrategy> findTheBestIndividual(int numberOfIndividuals);
}
