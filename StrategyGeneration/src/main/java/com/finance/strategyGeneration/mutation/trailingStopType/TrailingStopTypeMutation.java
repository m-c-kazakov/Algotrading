package com.finance.strategyGeneration.mutation.trailingStopType;

import com.finance.strategyGeneration.dataHolder.DataOfStrategy;
import com.finance.strategyGeneration.mutation.Mutation;
import com.finance.strategyGeneration.random.RandomTrailingStop;
import com.finance.strategyGeneration.strategyDescriptionParameters.TrailingStopConfigurationKey;
import com.finance.strategyGeneration.strategyDescriptionParameters.TrailingStopType;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Stream;

@Component
public class TrailingStopTypeMutation implements Mutation {

    @Override
    public Stream<DataOfStrategy> execute(DataOfStrategy parentDataOfStrategy) {
        TrailingStopType randomTrailingStopType = TrailingStopType.getRandomTrailingStopType();

        Map<TrailingStopConfigurationKey, Object> randomParamsForTrailingStop = RandomTrailingStop.getMapWithSupplierGeneratedRandomParams()
                .get(randomTrailingStopType)
                .get();

        DataOfStrategy childDataOfStrategy = parentDataOfStrategy
                .withTrailingStopType(randomTrailingStopType)
                .withTrailingStopConfigurationData(randomParamsForTrailingStop);

        return Stream.of(parentDataOfStrategy, childDataOfStrategy);
    }
}
