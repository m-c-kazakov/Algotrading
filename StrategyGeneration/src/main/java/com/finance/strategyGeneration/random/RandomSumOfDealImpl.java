package com.finance.strategyGeneration.random;

import com.finance.strategyGeneration.dataHolder.DataOfStrategy;
import com.finance.strategyGeneration.strategyDescriptionParameters.SumOfDealConfigurationKey;
import com.finance.strategyGeneration.strategyDescriptionParameters.SumOfDealType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RandomSumOfDealImpl implements RandomStrategyParams {

    static List<SumOfDealType> sumOfDealTypes = List.of(SumOfDealType.values());

    static Map<SumOfDealType, Consumer<DataOfStrategy.DataOfStrategyBuilder>> sumOfDealTypeConsumerMap = new EnumMap<>(
            SumOfDealType.class);

    static {
        sumOfDealTypeConsumerMap.put(SumOfDealType.PERCENT_OF_SCORE, dataOfStrategyBuilder -> {
            dataOfStrategyBuilder.sumOfDealType(SumOfDealType.PERCENT_OF_SCORE);
            dataOfStrategyBuilder.sumOfDealConfigurationData(
                    Map.of(SumOfDealConfigurationKey.PERCENT_OF_SCORE, ThreadLocalRandom.current()
                            .nextInt(1, 5)));
        });
    }

    @Override
    public void add(DataOfStrategy.DataOfStrategyBuilder dataOfStrategyBuilder) {

        SumOfDealType sumOfDealType = sumOfDealTypes.get(ThreadLocalRandom.current()
                .nextInt(sumOfDealTypes.size()));
        sumOfDealTypeConsumerMap.get(sumOfDealType)
                .accept(dataOfStrategyBuilder);

    }
}
