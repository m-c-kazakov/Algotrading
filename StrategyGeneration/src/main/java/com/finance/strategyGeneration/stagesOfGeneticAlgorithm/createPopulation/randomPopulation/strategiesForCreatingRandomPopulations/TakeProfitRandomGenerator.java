package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.strategiesForCreatingRandomPopulations;

import com.finance.dataHolder.DescriptionOfStrategy;
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
public class TakeProfitRandomGenerator implements RandomStrategyParams {

    // TODO Вынести паля и статические блоки инициализации
    Map<TakeProfitType, Consumer<DescriptionOfStrategy.DescriptionOfStrategyBuilder>> takeProfitTypeConsumerMap = new EnumMap<>(
            TakeProfitType.class);

    @Getter
    Map<TakeProfitType, Supplier<Map<TakeProfitConfigurationKey, Object>>> mapWithSupplierGeneratedRandomParams = new EnumMap<>(
            TakeProfitType.class);

    {
        mapWithSupplierGeneratedRandomParams.put(TakeProfitType.FIXED_TAKE_PROFIT,
                () -> Map.of(TakeProfitConfigurationKey.FIXED_TAKE_PROFIT, ThreadLocalRandom.current()
                        .nextInt(0, 300)));
    }

    {
        takeProfitTypeConsumerMap.put(TakeProfitType.FIXED_TAKE_PROFIT, dataOfStrategyBuilder -> {
            dataOfStrategyBuilder.takeProfitType(TakeProfitType.FIXED_TAKE_PROFIT);
            dataOfStrategyBuilder.takeProfitConfigurationData(
                    mapWithSupplierGeneratedRandomParams.get(TakeProfitType.FIXED_TAKE_PROFIT)
                            .get());
        });
    }

    @Override
    public void add(DescriptionOfStrategy.DescriptionOfStrategyBuilder dataOfStrategyBuilder) {

        takeProfitTypeConsumerMap
                .get(TakeProfitType.getRandomTakeProfitType())
                .accept(dataOfStrategyBuilder);
    }
}
