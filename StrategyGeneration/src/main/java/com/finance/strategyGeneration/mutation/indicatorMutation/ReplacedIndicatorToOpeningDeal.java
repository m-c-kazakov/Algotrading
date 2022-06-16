package com.finance.strategyGeneration.mutation.indicatorMutation;

import com.finance.strategyGeneration.dataHolder.DataOfStrategy;
import com.finance.strategyGeneration.mutation.Mutation;
import com.finance.strategyGeneration.random.indicator.RandomIndicatorUtils;
import com.finance.strategyGeneration.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyGeneration.strategyDescriptionParameters.DescriptionToOpenADeal;
import com.finance.strategyGeneration.strategyDescriptionParameters.indicators.Indicator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReplacedIndicatorToOpeningDeal implements Mutation {

    static List<CurrencyPair> CURRENCY_PAIRS = List.of(CurrencyPair.values());

    RandomIndicatorUtils randomIndicatorUtils;

    @Override
    public Stream<DataOfStrategy> execute(DataOfStrategy parentDataOfStrategy) {
        List<Indicator> indicators = parentDataOfStrategy.getIndicatorsDescriptionToOpenADeal();

        int numberOfReplacedItems = Math.max(ThreadLocalRandom.current().nextInt(indicators.size() / 2), 1);

        for (int i = 0; i < numberOfReplacedItems; i++) {

            int replacedIndex = ThreadLocalRandom.current()
                    .nextInt(indicators.size());
            // TODO Добавить ограничение на стратегии где для анализа используется только 1 валюта
            Indicator randomIndicator = randomIndicatorUtils.getRandomIndicator(
                    CURRENCY_PAIRS.get(ThreadLocalRandom.current()
                            .nextInt(CURRENCY_PAIRS.size())));
            indicators.set(replacedIndex, randomIndicator);
        }


        DataOfStrategy dataOfStrategyAfterMutation = parentDataOfStrategy.withDescriptionToOpenADeal(
                new DescriptionToOpenADeal(indicators));

        return Stream.of(parentDataOfStrategy, dataOfStrategyAfterMutation);
    }
}
