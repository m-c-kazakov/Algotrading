package com.finance.strategyGeneration.service;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;

import java.util.List;

public interface SpecificationOfStrategyService {
    void save(SpecificationOfStrategy specificationOfStrategy);

    void saveAll(List<DescriptionOfStrategy> populationAfterSelection);

}
