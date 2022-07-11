package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.strategiesForCreatingRandomPopulations;

import com.finance.strategyDescriptionParameters.TrailingStopConfigurationKey;
import com.finance.strategyDescriptionParameters.TrailingStopType;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.model.storage.ConfigurationStorage;
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
public class TrailingStopRandomGenerator implements RandomStrategyParams {

    // TODO Вынести паля и статические блоки инициализации
    Map<TrailingStopType, Consumer<SpecificationOfStrategy.SpecificationOfStrategyBuilder>> trailingStopTypeConsumerMap = new EnumMap<>(
            TrailingStopType.class);

    @Getter
    Map<TrailingStopType, Supplier<ConfigurationStorage<TrailingStopConfigurationKey>>> mapWithSupplierGeneratedRandomParams = new EnumMap<>(
            TrailingStopType.class);

    {
        mapWithSupplierGeneratedRandomParams.put(TrailingStopType.FIXED_TRAILING_STOP_TYPE,
                () -> new ConfigurationStorage<>(
                        Map.of(TrailingStopConfigurationKey.FIXED_TRAILING_STOP, ThreadLocalRandom.current()
                                .nextInt(1, 100)))
        );
    }

    {
        trailingStopTypeConsumerMap.put(TrailingStopType.FIXED_TRAILING_STOP_TYPE, dataOfStrategyBuilder -> {
            dataOfStrategyBuilder.trailingStopType(TrailingStopType.FIXED_TRAILING_STOP_TYPE);
            dataOfStrategyBuilder.trailingStopConfigurationData(
                    mapWithSupplierGeneratedRandomParams.get(TrailingStopType.FIXED_TRAILING_STOP_TYPE)
                            .get());
        });
    }

    @Override
    public void add(SpecificationOfStrategy.SpecificationOfStrategyBuilder specificationOfStrategyBuilder) {

        trailingStopTypeConsumerMap.get(TrailingStopType.getRandomTrailingStopType())
                .accept(specificationOfStrategyBuilder);

    }
}
