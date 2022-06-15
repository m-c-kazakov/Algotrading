package com.finance.strategyGeneration.random.indicator;

import com.finance.strategyGeneration.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyGeneration.strategyDescriptionParameters.TimeFrame;
import com.finance.strategyGeneration.strategyDescriptionParameters.indicators.Indicator;
import com.finance.strategyGeneration.strategyDescriptionParameters.indicators.IndicatorType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RandomIndicatorUtilsImpl implements RandomIndicatorUtils {

    static List<IndicatorType> indicatorTypes = List.of(IndicatorType.values());
    static List<TimeFrame> timeFrames = List.of(TimeFrame.values());

    RandomIndicatorParameters randomParametersByIndicatorType;

    @Override
    public Indicator getRandomIndicator(CurrencyPair currencyPair) {

        IndicatorType indicatorType = indicatorTypes.get(ThreadLocalRandom.current()
                .nextInt(indicatorTypes.size()));
        TimeFrame timeFrame = timeFrames.get(ThreadLocalRandom.current()
                .nextInt(timeFrames.size()));
        Map<String, String> parameters = randomParametersByIndicatorType.getRandomParametersByIndicatorType(
                indicatorType);

        return Indicator.builder()
                .indicatorType(indicatorType)
                .timeFrame(timeFrame)
                .currencyPair(currencyPair)
                .parameters(parameters)
                .build();
    }
}
