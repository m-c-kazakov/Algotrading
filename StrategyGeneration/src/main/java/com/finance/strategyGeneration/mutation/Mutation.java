package com.finance.strategyGeneration.mutation;

import com.finance.strategyGeneration.dataHolder.DataOfStrategy;

import java.util.stream.Stream;

public interface Mutation {

    Stream<DataOfStrategy> execute(DataOfStrategy parentDataOfStrategy);
}
