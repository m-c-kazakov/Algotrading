package com.finance.strategyGeneration.service;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SpecificationOfStrategyService {

    @Transactional
    SpecificationOfStrategy save(SpecificationOfStrategy specificationOfStrategy);

    List<SpecificationOfStrategy> saveAll(List<SpecificationOfStrategy> populationAfterSelection);

    List<SpecificationOfStrategy> findTheBestIndividual(int numberOfIndividuals);
}
