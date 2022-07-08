package com.finance.strategyGeneration.service;

import com.finance.dataHolder.DescriptionOfStrategy;

import java.util.Set;

public interface SpecificationOfStrategyService {
    void saveAll(Set<DescriptionOfStrategy> populationAfterSelection);

}
