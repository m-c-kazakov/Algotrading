package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.strategiesForCreatingRandomPopulations;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyDescriptionParameters.SumOfDealConfigurationKey;
import com.finance.strategyDescriptionParameters.SumOfDealType;
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
public class SumOfDealRandomGenerator implements RandomStrategyParams {

    // TODO Вынести паля и статические блоки инициализации
    Map<SumOfDealType, Consumer<DescriptionOfStrategy.DescriptionOfStrategyBuilder>> sumOfDealTypeConsumerMap = new EnumMap<>(
            SumOfDealType.class);

    @Getter
    Map<SumOfDealType, Supplier<Map<SumOfDealConfigurationKey, Object>>> mapWithSupplierGeneratedRandomParams = new EnumMap<>(
            SumOfDealType.class);

    {
        mapWithSupplierGeneratedRandomParams.put(SumOfDealType.PERCENT_OF_SCORE,
                () -> Map.of(SumOfDealConfigurationKey.PERCENT_OF_SCORE, ThreadLocalRandom.current()
                        .nextInt(1, 5)));
    }

    {
        sumOfDealTypeConsumerMap.put(SumOfDealType.PERCENT_OF_SCORE, dataOfStrategyBuilder -> {
            dataOfStrategyBuilder.sumOfDealType(SumOfDealType.PERCENT_OF_SCORE);
            dataOfStrategyBuilder.sumOfDealConfigurationData(
                    mapWithSupplierGeneratedRandomParams.get(SumOfDealType.PERCENT_OF_SCORE)
                            .get());
        });
    }

    @Override
    public void add(DescriptionOfStrategy.DescriptionOfStrategyBuilder dataOfStrategyBuilder) {

        sumOfDealTypeConsumerMap
                .get(SumOfDealType.getRandomSumOfDealType())
                .accept(dataOfStrategyBuilder);
    }
}
