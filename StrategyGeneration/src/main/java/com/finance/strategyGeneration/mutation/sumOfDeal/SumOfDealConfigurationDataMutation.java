package com.finance.strategyGeneration.mutation.sumOfDeal;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyDescriptionParameters.SumOfDealConfigurationKey;
import com.finance.strategyGeneration.mutation.Mutation;
import com.finance.strategyGeneration.random.RandomSumOfDeal;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Stream;

@Component
public class SumOfDealConfigurationDataMutation implements Mutation {

    @Override
    public Stream<DescriptionOfStrategy> execute(DescriptionOfStrategy parentDescriptionOfStrategy) {
        Map<SumOfDealConfigurationKey, Object> randomParamsForSumOfDeal = RandomSumOfDeal.getMapWithSupplierGeneratedRandomParams()
                .get(parentDescriptionOfStrategy.getSumOfDealType())
                .get();

        DescriptionOfStrategy childDescriptionOfStrategy = parentDescriptionOfStrategy
                .withSumOfDealConfigurationData(randomParamsForSumOfDeal);

        return Stream.of(parentDescriptionOfStrategy, childDescriptionOfStrategy);
    }
}
