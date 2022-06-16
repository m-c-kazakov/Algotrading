package com.finance.strategyGeneration.mutation;

import com.finance.strategyGeneration.dataHolder.DataOfStrategy;

import java.util.List;

public interface MutationOfIndividual {
    List<DataOfStrategy> execute(List<DataOfStrategy> populationAfterCrossing);
}
