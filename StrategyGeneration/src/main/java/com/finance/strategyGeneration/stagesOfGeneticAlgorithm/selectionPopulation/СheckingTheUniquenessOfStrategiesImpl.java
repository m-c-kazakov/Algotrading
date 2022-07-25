package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.selectionPopulation;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class СheckingTheUniquenessOfStrategiesImpl implements СheckingTheUniquenessOfStrategies {

    @Override
    public List<SpecificationOfStrategy> execute(List<SpecificationOfStrategy> populationAfterMutation) {
        Set<String> uniqueIdentifiers = new HashSet<>();

        return populationAfterMutation
                .stream()
                .filter(strategy -> uniqueIdentifiers.add(strategy.getHashCode()))
                .toList();
    }
}
