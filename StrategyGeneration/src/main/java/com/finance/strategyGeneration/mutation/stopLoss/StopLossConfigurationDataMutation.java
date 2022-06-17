package com.finance.strategyGeneration.mutation.stopLoss;

import com.finance.dataHolder.DataOfStrategy;
import com.finance.strategyDescriptionParameters.StopLossConfigurationKey;
import com.finance.strategyGeneration.mutation.Mutation;
import com.finance.strategyGeneration.random.RandomStopLoss;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Stream;

@Component
public class StopLossConfigurationDataMutation implements Mutation {

    @Override
    public Stream<DataOfStrategy> execute(DataOfStrategy parentDataOfStrategy) {

        Map<StopLossConfigurationKey, Object> randomParamsForStopLoss = RandomStopLoss.getMapWithSupplierGeneratedRandomParams()
                .get(parentDataOfStrategy.getStopLossType())
                .get();


        DataOfStrategy childDataOfStrategy = parentDataOfStrategy
                .withStopLossConfigurationData(randomParamsForStopLoss);

        return Stream.of(parentDataOfStrategy, childDataOfStrategy);
    }
}
