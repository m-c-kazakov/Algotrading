package com.finance.strategyGeneration.mutation;

import com.finance.dataHolder.DataOfStrategy;

import java.util.List;

public interface MutationOfIndividual {
    List<DataOfStrategy> execute(List<DataOfStrategy> populationAfterCrossing);
}
