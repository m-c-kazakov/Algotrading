package com.finance.strategyGeneration.mutation.indicatorMutation;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.DescriptionToOpenADeal;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import com.finance.strategyGeneration.mutation.Mutation;
import com.finance.strategyGeneration.random.indicator.RandomIndicatorUtils;
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
public class AddIndicatorToOpeningDeal implements Mutation {

    static List<CurrencyPair> CURRENCY_PAIRS = List.of(CurrencyPair.values());
    RandomIndicatorUtils randomIndicatorUtils;

    @Override
    public Stream<DescriptionOfStrategy> execute(DescriptionOfStrategy parentDescriptionOfStrategy) {
        List<Indicator> indicators = parentDescriptionOfStrategy.getIndicatorsDescriptionToOpenADeal();

        int numberOfAddedItems = Math.max(ThreadLocalRandom.current()
                .nextInt(indicators.size() / 3), 1);

        for (int i = 0; i < numberOfAddedItems; i++) {

            if (indicators.size() < 6) {
                // TODO Добавить ограничение на стратегии где для анализа используется только 1 валюта
                Indicator randomIndicator = randomIndicatorUtils.getRandomIndicator(
                        CURRENCY_PAIRS.get(ThreadLocalRandom.current()
                                .nextInt(CURRENCY_PAIRS.size())));
                indicators.add(randomIndicator);
            }
        }


        DescriptionOfStrategy descriptionOfStrategyAfterMutation = parentDescriptionOfStrategy.withDescriptionToOpenADeal(
                new DescriptionToOpenADeal(indicators));

        return Stream.of(parentDescriptionOfStrategy, descriptionOfStrategyAfterMutation);
    }
}
