package com.finance.strategyGeneration.service;

import com.finance.dataHolder.DescriptionOfStrategy;

import java.util.List;

public interface SpecificationOfStrategyServise {
    void saveAll(List<DescriptionOfStrategy> populationAfterSelection);

}
