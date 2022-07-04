package com.finance.strategyGeneration.mutation;

import com.finance.dataHolder.DescriptionOfStrategy;

import java.util.List;

public interface MutationOfIndividual {
    List<DescriptionOfStrategy> execute(List<DescriptionOfStrategy> populationAfterCrossing);
}
