package com.finance.strategyGeneration.mutation;

import com.finance.dataHolder.DescriptionOfStrategy;

import java.util.stream.Stream;

public interface Mutation {

    Stream<DescriptionOfStrategy> execute(DescriptionOfStrategy parentDescriptionOfStrategy);
}
