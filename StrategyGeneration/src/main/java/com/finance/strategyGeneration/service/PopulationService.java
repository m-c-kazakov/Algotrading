package com.finance.strategyGeneration.service;

import com.finance.dataHolder.DescriptionOfStrategy;

import java.util.List;

public interface PopulationService {
    List<DescriptionOfStrategy> findTheBestIndividual(int numberOfIndividuals);
}
