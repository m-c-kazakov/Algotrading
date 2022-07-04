package com.finance.strategyGeneration.random;

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
public class RandomSumOfDeal implements RandomStrategyParams {

    // TODO Вынести паля и статические блоки инициализации
    static Map<SumOfDealType, Consumer<DescriptionOfStrategy.DataOfStrategyBuilder>> sumOfDealTypeConsumerMap = new EnumMap<>(
            SumOfDealType.class);

    @Getter
    static Map<SumOfDealType, Supplier<Map<SumOfDealConfigurationKey, Object>>> mapWithSupplierGeneratedRandomParams = new EnumMap<>(
            SumOfDealType.class);

    static {
        mapWithSupplierGeneratedRandomParams.put(SumOfDealType.PERCENT_OF_SCORE,
                () -> Map.of(SumOfDealConfigurationKey.PERCENT_OF_SCORE, ThreadLocalRandom.current()
                        .nextInt(1, 5)));
    }

    static {
        sumOfDealTypeConsumerMap.put(SumOfDealType.PERCENT_OF_SCORE, dataOfStrategyBuilder -> {
            dataOfStrategyBuilder.sumOfDealType(SumOfDealType.PERCENT_OF_SCORE);
            dataOfStrategyBuilder.sumOfDealConfigurationData(
                    mapWithSupplierGeneratedRandomParams.get(SumOfDealType.PERCENT_OF_SCORE)
                            .get());
        });
    }

    @Override
    public void add(DescriptionOfStrategy.DataOfStrategyBuilder dataOfStrategyBuilder) {

        sumOfDealTypeConsumerMap.get(SumOfDealType.getRandomSumOfDealType())
                .accept(dataOfStrategyBuilder);

    }
}
