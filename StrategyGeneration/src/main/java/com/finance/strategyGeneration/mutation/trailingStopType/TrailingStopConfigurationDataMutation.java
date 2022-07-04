package com.finance.strategyGeneration.mutation.trailingStopType;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyDescriptionParameters.TrailingStopConfigurationKey;
import com.finance.strategyGeneration.mutation.Mutation;
import com.finance.strategyGeneration.random.RandomTrailingStop;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Stream;

@Component
public class TrailingStopConfigurationDataMutation implements Mutation {
    @Override
    public Stream<DescriptionOfStrategy> execute(DescriptionOfStrategy parentDescriptionOfStrategy) {

        Map<TrailingStopConfigurationKey, Object> randomParamsForStopLoss = RandomTrailingStop.getMapWithSupplierGeneratedRandomParams()
                .get(parentDescriptionOfStrategy.getTrailingStopType())
                .get();

        DescriptionOfStrategy childDescriptionOfStrategy = parentDescriptionOfStrategy
                .withTrailingStopConfigurationData(randomParamsForStopLoss);

        return Stream.of(parentDescriptionOfStrategy, childDescriptionOfStrategy);
    }
}
