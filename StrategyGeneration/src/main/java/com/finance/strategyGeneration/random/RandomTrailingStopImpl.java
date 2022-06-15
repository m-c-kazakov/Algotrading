package com.finance.strategyGeneration.random;

import com.finance.strategyGeneration.dataHolder.DataOfStrategy;
import com.finance.strategyGeneration.strategyDescriptionParameters.TrailingStopConfigurationKey;
import com.finance.strategyGeneration.strategyDescriptionParameters.TrailingStopType;
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
public class RandomTrailingStopImpl implements RandomStrategyParams {

    static List<TrailingStopType> trailingStopTypes = List.of(TrailingStopType.values());


    static Map<TrailingStopType, Consumer<DataOfStrategy.DataOfStrategyBuilder>> trailingStopTypeConsumerMap = new EnumMap<>(
            TrailingStopType.class);

    static {
        trailingStopTypeConsumerMap.put(TrailingStopType.FIXED, dataOfStrategyBuilder -> {
            dataOfStrategyBuilder.trailingStopType(TrailingStopType.FIXED);
            dataOfStrategyBuilder.trailingStopConfigurationData(
                    Map.of(TrailingStopConfigurationKey.FIXED, ThreadLocalRandom.current()
                            .nextInt(1, 100)));
        });
    }

    @Override
    public void add(DataOfStrategy.DataOfStrategyBuilder dataOfStrategyBuilder) {

        TrailingStopType trailingStopType = trailingStopTypes.get(ThreadLocalRandom.current()
                .nextInt(trailingStopTypes.size()));
        trailingStopTypeConsumerMap.get(trailingStopType)
                .accept(dataOfStrategyBuilder);

    }
}
