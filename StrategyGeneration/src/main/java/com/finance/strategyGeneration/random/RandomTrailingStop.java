package com.finance.strategyGeneration.random;

import com.finance.strategyGeneration.dataHolder.DataOfStrategy;
import com.finance.strategyGeneration.strategyDescriptionParameters.TrailingStopConfigurationKey;
import com.finance.strategyGeneration.strategyDescriptionParameters.TrailingStopType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RandomTrailingStop implements RandomStrategyParams {

    // TODO Вынести паля и статические блоки инициализации
    static Map<TrailingStopType, Consumer<DataOfStrategy.DataOfStrategyBuilder>> trailingStopTypeConsumerMap = new EnumMap<>(
            TrailingStopType.class);

    @Getter
    static Map<TrailingStopType, Supplier<Map<TrailingStopConfigurationKey, Object>>> mapWithSupplierGeneratedRandomParams = new EnumMap<>(
            TrailingStopType.class);

    static {
        mapWithSupplierGeneratedRandomParams.put(TrailingStopType.FIXED,
                () -> Map.of(TrailingStopConfigurationKey.FIXED, ThreadLocalRandom.current()
                        .nextInt(1, 100)));
    }

    static {
        trailingStopTypeConsumerMap.put(TrailingStopType.FIXED, dataOfStrategyBuilder -> {
            dataOfStrategyBuilder.trailingStopType(TrailingStopType.FIXED);
            dataOfStrategyBuilder.trailingStopConfigurationData(
                    mapWithSupplierGeneratedRandomParams.get(TrailingStopType.FIXED)
                            .get());
        });
    }

    @Override
    public void add(DataOfStrategy.DataOfStrategyBuilder dataOfStrategyBuilder) {

        trailingStopTypeConsumerMap.get(TrailingStopType.getRandomTrailingStopType())
                .accept(dataOfStrategyBuilder);

    }
}
