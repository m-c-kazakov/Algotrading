package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.strategiesForCreatingRandomPopulations;

import com.finance.strategyDescriptionParameters.StopLossConfigurationKey;
import com.finance.strategyDescriptionParameters.StopLossType;
import com.finance.strategyGeneration.model.ConfigurationStorage;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
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
public class StopLossRandomGenerator implements RandomStrategyParams {

    // TODO Вынести паля и статические блоки инициализации
    Map<StopLossType, Consumer<SpecificationOfStrategy.SpecificationOfStrategyBuilder>> stopLossTypeConsumerMap = new EnumMap<>(
            StopLossType.class);

    @Getter
    Map<StopLossType, Supplier<ConfigurationStorage<StopLossConfigurationKey>>> mapWithSupplierGeneratedRandomParams = new EnumMap<>(
            StopLossType.class);

    {
        mapWithSupplierGeneratedRandomParams.put(StopLossType.FIXED_STOP_LOSS,
                () -> new ConfigurationStorage<>(
                        Map.of(StopLossConfigurationKey.FIXED_STOP_LOSS, ThreadLocalRandom.current().nextInt(1, 100))));
    }

    {
        stopLossTypeConsumerMap.put(StopLossType.FIXED_STOP_LOSS, dataOfStrategyBuilder -> {
            dataOfStrategyBuilder.stopLossType(StopLossType.FIXED_STOP_LOSS);
            dataOfStrategyBuilder.stopLossConfigurationData(
                    mapWithSupplierGeneratedRandomParams.get(StopLossType.FIXED_STOP_LOSS).get());
        });
    }

    @Override
    public void add(SpecificationOfStrategy.SpecificationOfStrategyBuilder dataOfStrategyBuilder) {

        stopLossTypeConsumerMap.get(StopLossType.getRandomStopLossType()).accept(dataOfStrategyBuilder);
    }
}
