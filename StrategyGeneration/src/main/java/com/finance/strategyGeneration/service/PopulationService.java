package com.finance.strategyGeneration.service;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;

import java.util.List;

public interface PopulationService {
    List<SpecificationOfStrategy> findTheBestIndividual(int numberOfIndividuals);
}
