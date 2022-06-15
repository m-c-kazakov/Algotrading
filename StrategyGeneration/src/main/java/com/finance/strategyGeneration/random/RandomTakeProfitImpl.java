package com.finance.strategyGeneration.random;

import com.finance.strategyGeneration.dataHolder.DataOfStrategy;
import com.finance.strategyGeneration.strategyDescriptionParameters.TakeProfitConfigurationKey;
import com.finance.strategyGeneration.strategyDescriptionParameters.TakeProfitType;
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
public class RandomTakeProfitImpl implements RandomStrategyParams {

    static List<TakeProfitType> takeProfitTypes = List.of(TakeProfitType.values());

    static Map<TakeProfitType, Consumer<DataOfStrategy.DataOfStrategyBuilder>> takeProfitTypeConsumerMap = new EnumMap<>(
            TakeProfitType.class);

    static {
        takeProfitTypeConsumerMap.put(TakeProfitType.FIXED, dataOfStrategyBuilder -> {
            dataOfStrategyBuilder.takeProfitType(TakeProfitType.FIXED);
            dataOfStrategyBuilder.takeProfitConfigurationData(
                    Map.of(TakeProfitConfigurationKey.FIXED, ThreadLocalRandom.current()
                            .nextInt(0, 300)));
        });
    }

    @Override
    public void add(DataOfStrategy.DataOfStrategyBuilder dataOfStrategyBuilder) {
        TakeProfitType takeProfitType = takeProfitTypes.get(ThreadLocalRandom.current()
                .nextInt(takeProfitTypes.size()));
        takeProfitTypeConsumerMap.get(takeProfitType)
                .accept(dataOfStrategyBuilder);
    }
}
