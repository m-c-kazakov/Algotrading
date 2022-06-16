package com.finance.strategyGeneration.populationSelection;

import com.finance.strategyGeneration.dataHolder.DataOfStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class СheckingTheUniquenessOfStrategiesImpl implements СheckingTheUniquenessOfStrategies {
    @Override
    public List<DataOfStrategy> execute(List<DataOfStrategy> populationAfterMutation) {
        return populationAfterMutation.stream()
                .distinct()
                .toList();
    }
}
