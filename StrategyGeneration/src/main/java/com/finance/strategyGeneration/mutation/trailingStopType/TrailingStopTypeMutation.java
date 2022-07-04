package com.finance.strategyGeneration.mutation.trailingStopType;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyDescriptionParameters.TrailingStopConfigurationKey;
import com.finance.strategyDescriptionParameters.TrailingStopType;
import com.finance.strategyGeneration.mutation.Mutation;
import com.finance.strategyGeneration.random.RandomTrailingStop;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Stream;

@Component
public class TrailingStopTypeMutation implements Mutation {

    @Override
    public Stream<DescriptionOfStrategy> execute(DescriptionOfStrategy parentDescriptionOfStrategy) {
        TrailingStopType randomTrailingStopType = TrailingStopType.getRandomTrailingStopType();

        Map<TrailingStopConfigurationKey, Object> randomParamsForTrailingStop = RandomTrailingStop.getMapWithSupplierGeneratedRandomParams()
                .get(randomTrailingStopType)
                .get();

        DescriptionOfStrategy childDescriptionOfStrategy = parentDescriptionOfStrategy
                .withTrailingStopType(randomTrailingStopType)
                .withTrailingStopConfigurationData(randomParamsForTrailingStop);

        return Stream.of(parentDescriptionOfStrategy, childDescriptionOfStrategy);
    }
}
