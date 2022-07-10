package com.finance.strategyGeneration.service;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;

import java.util.List;

public interface SpecificationOfStrategyService {

    List<SpecificationOfStrategy> saveAll(List<SpecificationOfStrategy> populationAfterSelection);

    List<SpecificationOfStrategy> findTheBestIndividual(int numberOfIndividuals);
}
