package com.finance.strategyGeneration.random;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyDescriptionParameters.CandlesInformation;
import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.TimeFrame;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import com.finance.strategyGeneration.random.indicator.RandomIndicatorUtils;
import com.finance.strategyGeneration.service.InformationOfIndicatorService;
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
    InformationOfIndicatorService informationOfIndicatorService;

    @Override
    public void add(DescriptionOfStrategy.DescriptionOfStrategyBuilder dataOfStrategyBuilder) {

        CurrencyPair currencyPair = currencyPairs.get(ThreadLocalRandom.current()
                .nextInt(currencyPairs.size()));


        List<Indicator> descriptionToOpenADeal = generateIndicators(currencyPair);
        List<Indicator> descriptionToCloseADeal = generateIndicators(currencyPair);
        dataOfStrategyBuilder
                .descriptionToOpenADeal(descriptionToOpenADeal)
                .descriptionToCloseADeal(descriptionToCloseADeal);

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

    private List<Indicator> generateIndicators(CurrencyPair currencyPair) {
        int numberOfIndicators = ThreadLocalRandom.current()
                .nextInt(1, 6);
        return Stream.iterate(0, integer -> integer < numberOfIndicators, integer -> integer + 1)
                .map(integer -> randomIndicatorUtils.getRandomIndicator(currencyPair))
                .map(informationOfIndicatorService::save)
                .toList();
    }
}
