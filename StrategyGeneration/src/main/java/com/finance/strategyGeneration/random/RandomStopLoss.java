package com.finance.strategyGeneration.random;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyDescriptionParameters.StopLossConfigurationKey;
import com.finance.strategyDescriptionParameters.StopLossType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RandomStopLoss implements RandomStrategyParams {

    // TODO Вынести паля и статические блоки инициализации
    static Map<StopLossType, Consumer<DescriptionOfStrategy.DescriptionOfStrategyBuilder>> stopLossTypeConsumerMap = new EnumMap<>(
            StopLossType.class);

    @Getter
    static Map<StopLossType, Supplier<Map<StopLossConfigurationKey, Object>>> mapWithSupplierGeneratedRandomParams = new EnumMap<>(
            StopLossType.class);

    static {
        mapWithSupplierGeneratedRandomParams.put(StopLossType.FIXED_STOP_LOSS,
                () -> Map.of(StopLossConfigurationKey.FIXED_STOP_LOSS, ThreadLocalRandom.current()
                        .nextInt(1, 100)));
    }

    static {
        stopLossTypeConsumerMap.put(StopLossType.FIXED_STOP_LOSS, dataOfStrategyBuilder -> {
            dataOfStrategyBuilder.stopLossType(StopLossType.FIXED_STOP_LOSS);
            dataOfStrategyBuilder.stopLossConfigurationData(
                    mapWithSupplierGeneratedRandomParams.get(StopLossType.FIXED_STOP_LOSS)
                            .get());
        });
    }

    @Override
    public void add(DescriptionOfStrategy.DescriptionOfStrategyBuilder dataOfStrategyBuilder) {

        stopLossTypeConsumerMap.get(StopLossType.getRandomStopLossType())
                .accept(dataOfStrategyBuilder);


    }
}
