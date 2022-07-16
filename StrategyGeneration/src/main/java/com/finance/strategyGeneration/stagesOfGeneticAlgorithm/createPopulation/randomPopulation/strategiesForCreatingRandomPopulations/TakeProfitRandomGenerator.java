package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.strategiesForCreatingRandomPopulations;

import com.finance.strategyDescriptionParameters.TakeProfitConfigurationKey;
import com.finance.strategyDescriptionParameters.TakeProfitType;
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
public class TakeProfitRandomGenerator implements RandomStrategyParams {

    // TODO Вынести паля и статические блоки инициализации
    Map<TakeProfitType, Consumer<SpecificationOfStrategy.SpecificationOfStrategyBuilder>> takeProfitTypeConsumerMap = new EnumMap<>(
            TakeProfitType.class);

    @Getter
    Map<TakeProfitType, Supplier<ConfigurationStorage<TakeProfitConfigurationKey>>> mapWithSupplierGeneratedRandomParams = new EnumMap<>(
            TakeProfitType.class);

    {
        mapWithSupplierGeneratedRandomParams.put(TakeProfitType.FIXED_TAKE_PROFIT,
                () -> new ConfigurationStorage<>(Map.of(TakeProfitConfigurationKey.FIXED_TAKE_PROFIT,
                        String.valueOf(ThreadLocalRandom.current().nextInt(0, 300)))));
    }

    {
        takeProfitTypeConsumerMap.put(TakeProfitType.FIXED_TAKE_PROFIT, dataOfStrategyBuilder -> {
            dataOfStrategyBuilder.takeProfitType(TakeProfitType.FIXED_TAKE_PROFIT);
            dataOfStrategyBuilder.takeProfitConfigurationData(
                    mapWithSupplierGeneratedRandomParams.get(TakeProfitType.FIXED_TAKE_PROFIT).get());
        });
    }

    @Override
    public void add(SpecificationOfStrategy.SpecificationOfStrategyBuilder dataOfStrategyBuilder) {

        takeProfitTypeConsumerMap.get(TakeProfitType.getRandomTakeProfitType()).accept(dataOfStrategyBuilder);
    }
}
