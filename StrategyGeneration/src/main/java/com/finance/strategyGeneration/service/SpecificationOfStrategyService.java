package com.finance.strategyGeneration.service;

import com.finance.dataHolder.DescriptionOfStrategy;

import java.util.List;

public interface SpecificationOfStrategyService {
    void saveAll(List<DescriptionOfStrategy> populationAfterSelection);

}
