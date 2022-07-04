package com.finance.strategyGeneration.mutation.stopLoss;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyDescriptionParameters.StopLossConfigurationKey;
import com.finance.strategyGeneration.mutation.Mutation;
import com.finance.strategyGeneration.random.RandomStopLoss;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Stream;

@Component
public class StopLossConfigurationDataMutation implements Mutation {

    @Override
    public Stream<DescriptionOfStrategy> execute(DescriptionOfStrategy parentDescriptionOfStrategy) {

        Map<StopLossConfigurationKey, Object> randomParamsForStopLoss = RandomStopLoss.getMapWithSupplierGeneratedRandomParams()
                .get(parentDescriptionOfStrategy.getStopLossType())
                .get();


        DescriptionOfStrategy childDescriptionOfStrategy = parentDescriptionOfStrategy
                .withStopLossConfigurationData(randomParamsForStopLoss);

        return Stream.of(parentDescriptionOfStrategy, childDescriptionOfStrategy);
    }
}
