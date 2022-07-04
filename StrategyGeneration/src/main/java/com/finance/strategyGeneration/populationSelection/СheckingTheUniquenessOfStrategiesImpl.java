package com.finance.strategyGeneration.populationSelection;

import com.finance.dataHolder.DescriptionOfStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class СheckingTheUniquenessOfStrategiesImpl implements СheckingTheUniquenessOfStrategies {
    @Override
    public List<DescriptionOfStrategy> execute(List<DescriptionOfStrategy> populationAfterMutation) {
        return populationAfterMutation.stream()
                .distinct()
                .toList();
    }
}
