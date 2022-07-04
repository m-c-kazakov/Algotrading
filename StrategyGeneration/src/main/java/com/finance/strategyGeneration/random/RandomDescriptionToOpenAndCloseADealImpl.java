package com.finance.strategyGeneration.random;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyDescriptionParameters.*;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import com.finance.strategyGeneration.random.indicator.RandomIndicatorUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import static org.springframework.util.CollectionUtils.isEmpty;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RandomDescriptionToOpenAndCloseADealImpl implements RandomStrategyParams {

    static List<CurrencyPair> currencyPairs = List.of(CurrencyPair.values());
    RandomIndicatorUtils randomIndicatorUtils;

    @Override
    public void add(DescriptionOfStrategy.DescriptionOfStrategyBuilder dataOfStrategyBuilder) {

        CurrencyPair currencyPair = currencyPairs.get(ThreadLocalRandom.current()
                .nextInt(currencyPairs.size()));


        List<Indicator> descriptionToOpenADeal = getDescriptionToADeal(currencyPair);
        List<Indicator> descriptionToCloseADeal = getDescriptionToADeal(currencyPair);
        dataOfStrategyBuilder.descriptionToOpenADeal(DescriptionToOpenADeal.builder().indicators(descriptionToOpenADeal).build());
        dataOfStrategyBuilder.descriptionToCloseADeal(DescriptionToCloseADeal.builder().indicators(descriptionToCloseADeal).build());

        TimeFrame timeFrame = findMinimalTimeFrame(descriptionToOpenADeal, descriptionToCloseADeal);
        dataOfStrategyBuilder.candlesInformation(CandlesInformation.builder().currencyPair(currencyPair).timeFrame(timeFrame).build());

    }

    private TimeFrame findMinimalTimeFrame(List<Indicator> descriptionToOpenADeal,
                                           List<Indicator> descriptionToCloseADeal) {
        List<TimeFrame> timeFrames = Stream.of(descriptionToOpenADeal, descriptionToCloseADeal)
                .filter(indicators -> !isEmpty(indicators))
                .flatMap(List::stream)
                .map(Indicator::getTimeFrame)
                .toList();

        return TimeFrame.getMinimalTimeFrame(timeFrames);
    }

    private List<Indicator> getDescriptionToADeal(CurrencyPair currencyPair) {
        int numberOfIndicators = ThreadLocalRandom.current()
                .nextInt(1, 6);
        return Stream.iterate(0, integer -> integer < numberOfIndicators, integer -> integer + 1)
                .map(integer -> randomIndicatorUtils.getRandomIndicator(currencyPair))
                .toList();
    }
}
