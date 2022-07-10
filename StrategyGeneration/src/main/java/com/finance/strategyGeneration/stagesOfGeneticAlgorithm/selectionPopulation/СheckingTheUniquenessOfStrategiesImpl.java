package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.selectionPopulation;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.repository.SpecificationOfStrategyRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class СheckingTheUniquenessOfStrategiesImpl implements СheckingTheUniquenessOfStrategies {

    SpecificationOfStrategyRepository specificationOfStrategyRepository;

    @Override
    public List<SpecificationOfStrategy> execute(List<SpecificationOfStrategy> populationAfterMutation) {

        return populationAfterMutation
                .stream()
                .map(specificationOfStrategy -> specificationOfStrategy
                        .withId(null)
                        .withHashCode(specificationOfStrategy.hashCode()))
                .distinct()
                .filter(specificationOfStrategy -> !specificationOfStrategyRepository.existsByHashCode(
                        specificationOfStrategy.getHashCode()))
                .toList();
    }
}
