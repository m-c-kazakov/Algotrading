package com.finance.strategyGeneration.mutation.trailingStopType;

import com.finance.dataHolder.DataOfStrategy;
import com.finance.strategyDescriptionParameters.TrailingStopConfigurationKey;
import com.finance.strategyGeneration.mutation.Mutation;
import com.finance.strategyGeneration.random.RandomTrailingStop;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Stream;

@Component
public class TrailingStopConfigurationDataMutation implements Mutation {
    @Override
    public Stream<DataOfStrategy> execute(DataOfStrategy parentDataOfStrategy) {

        Map<TrailingStopConfigurationKey, Object> randomParamsForStopLoss = RandomTrailingStop.getMapWithSupplierGeneratedRandomParams()
                .get(parentDataOfStrategy.getTrailingStopType())
                .get();

        DataOfStrategy childDataOfStrategy = parentDataOfStrategy
                .withTrailingStopConfigurationData(randomParamsForStopLoss);

        return Stream.of(parentDataOfStrategy, childDataOfStrategy);
    }
}
