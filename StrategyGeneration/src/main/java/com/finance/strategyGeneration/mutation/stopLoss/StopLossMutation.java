package com.finance.strategyGeneration.mutation.stopLoss;

import com.finance.strategyGeneration.dataHolder.DataOfStrategy;
import com.finance.strategyGeneration.mutation.Mutation;
import com.finance.strategyGeneration.random.RandomStopLoss;
import com.finance.strategyGeneration.strategyDescriptionParameters.StopLossConfigurationKey;
import com.finance.strategyGeneration.strategyDescriptionParameters.StopLossType;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Stream;

@Component
public class StopLossMutation implements Mutation {


    @Override
    public Stream<DataOfStrategy> execute(DataOfStrategy parentDataOfStrategy) {

        StopLossType randomStopLossType = StopLossType.getRandomStopLossType();
        Map<StopLossConfigurationKey, Object> randomParamsForStopLoss = RandomStopLoss.getMapWithSupplierGeneratedRandomParams()
                .get(randomStopLossType)
                .get();


        DataOfStrategy childDataOfStrategy = parentDataOfStrategy
                .withStopLossType(randomStopLossType)
                .withStopLossConfigurationData(randomParamsForStopLoss);

        return Stream.of(parentDataOfStrategy, childDataOfStrategy);
    }
}
