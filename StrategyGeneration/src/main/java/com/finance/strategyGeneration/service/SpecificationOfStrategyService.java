package com.finance.strategyGeneration.service;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface SpecificationOfStrategyService {

    Optional<SpecificationOfStrategy> findByHashCode(String hashCode);

    @Transactional
    SpecificationOfStrategy save(SpecificationOfStrategy specificationOfStrategy);

    List<SpecificationOfStrategy> saveAll(List<SpecificationOfStrategy> populationAfterSelection);

    List<SpecificationOfStrategy> findTheBestIndividual(int numberOfIndividuals);

    List<SpecificationOfStrategy> findMaximumScoreStrategy(int numberOfIndividuals);

    Integer findTheNumberOfUntestedStrategies();
}
