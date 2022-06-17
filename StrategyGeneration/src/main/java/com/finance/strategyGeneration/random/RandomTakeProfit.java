package com.finance.strategyGeneration.random;

import com.finance.dataHolder.DataOfStrategy;
import com.finance.strategyDescriptionParameters.TakeProfitConfigurationKey;
import com.finance.strategyDescriptionParameters.TakeProfitType;
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
public class RandomTakeProfit implements RandomStrategyParams {

    // TODO Вынести паля и статические блоки инициализации
    static Map<TakeProfitType, Consumer<DataOfStrategy.DataOfStrategyBuilder>> takeProfitTypeConsumerMap = new EnumMap<>(
            TakeProfitType.class);

    @Getter
    static Map<TakeProfitType, Supplier<Map<TakeProfitConfigurationKey, Object>>> mapWithSupplierGeneratedRandomParams = new EnumMap<>(
            TakeProfitType.class);

    static {
        mapWithSupplierGeneratedRandomParams.put(TakeProfitType.FIXED,
                () -> Map.of(TakeProfitConfigurationKey.FIXED, ThreadLocalRandom.current()
                        .nextInt(0, 300)));
    }

    static {
        takeProfitTypeConsumerMap.put(TakeProfitType.FIXED, dataOfStrategyBuilder -> {
            dataOfStrategyBuilder.takeProfitType(TakeProfitType.FIXED);
            dataOfStrategyBuilder.takeProfitConfigurationData(
                    mapWithSupplierGeneratedRandomParams.get(TakeProfitType.FIXED)
                            .get());
        });
    }

    @Override
    public void add(DataOfStrategy.DataOfStrategyBuilder dataOfStrategyBuilder) {

        takeProfitTypeConsumerMap.get(TakeProfitType.getRandomTakeProfitType())
                .accept(dataOfStrategyBuilder);
    }
}
