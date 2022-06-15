package com.finance.strategyGeneration.random;

import com.finance.strategyGeneration.dataHolder.DataOfStrategy;
import com.finance.strategyGeneration.strategyDescriptionParameters.StopLossConfigurationKey;
import com.finance.strategyGeneration.strategyDescriptionParameters.StopLossType;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RandomStopLossImpl implements RandomStrategyParams {

    static List<StopLossType> stopLossTypes = List.of(StopLossType.values());

    static Map<StopLossType, Consumer<DataOfStrategy.DataOfStrategyBuilder>> stopLossTypeConsumerMap = new EnumMap<>(
            StopLossType.class);

    static {
        stopLossTypeConsumerMap.put(StopLossType.FIXED, dataOfStrategyBuilder -> {
            dataOfStrategyBuilder.stopLossType(StopLossType.FIXED);
            dataOfStrategyBuilder.stopLossConfigurationData(
                    Map.of(StopLossConfigurationKey.FIXED, ThreadLocalRandom.current()
                            .nextInt(1, 100)));
        });
    }

    @Override
    public void add(DataOfStrategy.DataOfStrategyBuilder dataOfStrategyBuilder) {

        StopLossType stopLossType = stopLossTypes.get(ThreadLocalRandom.current()
                .nextInt(stopLossTypes.size()));

        stopLossTypeConsumerMap.get(stopLossType)
                .accept(dataOfStrategyBuilder);


    }
}
