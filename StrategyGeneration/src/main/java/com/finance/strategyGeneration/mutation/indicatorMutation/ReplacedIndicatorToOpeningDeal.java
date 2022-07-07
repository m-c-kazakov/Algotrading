package com.finance.strategyGeneration.mutation.indicatorMutation;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import com.finance.strategyGeneration.mutation.Mutation;
import com.finance.strategyGeneration.random.indicator.RandomIndicatorUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
    public Stream<DescriptionOfStrategy> execute(DescriptionOfStrategy parentDescriptionOfStrategy) {
        List<Indicator> indicators = new ArrayList<>(parentDescriptionOfStrategy.getIndicatorsDescriptionToOpenADeal());

        int bound = Math.max(indicators.size() / 2, 1);
        int numberOfReplacedItems = Math.max(ThreadLocalRandom.current().nextInt(bound), 1);

        for (int i = 0; i < numberOfReplacedItems; i++) {

            int replacedIndex = ThreadLocalRandom.current().nextInt(indicators.size());
            // TODO Добавить ограничение на стратегии где для анализа используется только 1 валюта
            Indicator randomIndicator = randomIndicatorUtils.getRandomIndicator(
                    CURRENCY_PAIRS.get(ThreadLocalRandom.current().nextInt(CURRENCY_PAIRS.size())));
            indicators.set(replacedIndex, randomIndicator);
        }


        DescriptionOfStrategy descriptionOfStrategyAfterMutation = parentDescriptionOfStrategy.withDescriptionToOpenADeal(
                indicators);

        return Stream.of(parentDescriptionOfStrategy, descriptionOfStrategyAfterMutation);
    }
}
