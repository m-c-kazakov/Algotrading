package com.finance.strategyGeneration.mutation.sumOfDeal;

import com.finance.strategyGeneration.dataHolder.DataOfStrategy;
import com.finance.strategyGeneration.mutation.Mutation;
import com.finance.strategyGeneration.random.RandomSumOfDeal;
import com.finance.strategyGeneration.strategyDescriptionParameters.SumOfDealConfigurationKey;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Stream;

@Component
public class SumOfDealConfigurationDataMutation implements Mutation {

    @Override
    public Stream<DataOfStrategy> execute(DataOfStrategy parentDataOfStrategy) {
        Map<SumOfDealConfigurationKey, Object> randomParamsForSumOfDeal = RandomSumOfDeal.getMapWithSupplierGeneratedRandomParams()
                .get(parentDataOfStrategy.getSumOfDealType())
                .get();

        DataOfStrategy childDataOfStrategy = parentDataOfStrategy
                .withSumOfDealConfigurationData(randomParamsForSumOfDeal);

        return Stream.of(parentDataOfStrategy, childDataOfStrategy);
    }
}
