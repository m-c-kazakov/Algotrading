package com.finance.strategyGeneration.random;

import com.finance.dataHolder.DataOfStrategy;
import com.finance.strategyDescriptionParameters.*;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import com.finance.strategyGeneration.random.indicator.RandomIndicatorUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RandomDescriptionToOpenAndCloseADealImpl implements RandomStrategyParams {

    static List<CurrencyPair> currencyPairs = List.of(CurrencyPair.values());
    RandomIndicatorUtils randomIndicatorUtils;

    @Override
    public void add(DataOfStrategy.DataOfStrategyBuilder dataOfStrategyBuilder) {

        CurrencyPair currencyPair = currencyPairs.get(ThreadLocalRandom.current()
                .nextInt(currencyPairs.size()));


        List<Indicator> descriptionToOpenADeal = getDescriptionToADeal(currencyPair);
        List<Indicator> descriptionToCloseADeal = getDescriptionToADeal(currencyPair);
        dataOfStrategyBuilder.descriptionToOpenADeal(new DescriptionToOpenADeal(descriptionToOpenADeal));
        dataOfStrategyBuilder.descriptionToCloseADeal(new DescriptionToCloseADeal(descriptionToCloseADeal));

        TimeFrame timeFrame = findMinimalTimeFrame(descriptionToOpenADeal, descriptionToCloseADeal);
        dataOfStrategyBuilder.candlesInformation(new CandlesInformation(currencyPair, timeFrame));

    }

    private TimeFrame findMinimalTimeFrame(List<Indicator> descriptionToOpenADeal,
                                           List<Indicator> descriptionToCloseADeal) {
        return Stream.of(descriptionToOpenADeal, descriptionToCloseADeal)
                .flatMap(List::stream)
                .map(Indicator::getTimeFrame)
                .sorted(Comparator.comparing(TimeFrame::getSize))
                .findFirst()
                .get();
    }

    private List<Indicator> getDescriptionToADeal(CurrencyPair currencyPair) {
        int numberOfIndicators = ThreadLocalRandom.current()
                .nextInt(1, 6);
        return Stream.iterate(0, integer -> integer < numberOfIndicators, integer -> integer + 1)
                .map(integer -> randomIndicatorUtils.getRandomIndicator(currencyPair))
                .toList();
    }
}
