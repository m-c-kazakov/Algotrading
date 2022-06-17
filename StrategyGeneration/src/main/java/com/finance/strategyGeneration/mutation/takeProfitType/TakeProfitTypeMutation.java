package com.finance.strategyGeneration.mutation.takeProfitType;

import com.finance.dataHolder.DataOfStrategy;
import com.finance.strategyDescriptionParameters.TakeProfitConfigurationKey;
import com.finance.strategyDescriptionParameters.TakeProfitType;
import com.finance.strategyGeneration.mutation.Mutation;
import com.finance.strategyGeneration.random.RandomTakeProfit;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Stream;

@Component
public class TakeProfitTypeMutation implements Mutation {
    @Override
    public Stream<DataOfStrategy> execute(DataOfStrategy parentDataOfStrategy) {

        TakeProfitType randomTakeProfitType = TakeProfitType.getRandomTakeProfitType();
        Map<TakeProfitConfigurationKey, Object> randomParamsForTakeProfit = RandomTakeProfit.getMapWithSupplierGeneratedRandomParams()
                .get(randomTakeProfitType)
                .get();

        DataOfStrategy childDataOfStrategy = parentDataOfStrategy
                .withTakeProfitType(randomTakeProfitType)
                .withTakeProfitConfigurationData(randomParamsForTakeProfit);

        return Stream.of(parentDataOfStrategy, childDataOfStrategy);
    }
}
