package com.finance.strategyGeneration.random.indicator;

import com.finance.strategyDescriptionParameters.CandlesInformation;
import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.TimeFrame;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import com.finance.strategyDescriptionParameters.indicators.IndicatorType;
import com.finance.strategyGeneration.service.CandlesInformationService;
import com.finance.strategyGeneration.service.InformationOfIndicatorService;
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
    CandlesInformationService candlesInformationService;
    InformationOfIndicatorService informationOfIndicatorService;

    @Override
    public Indicator getRandomIndicator(CurrencyPair currencyPair) {

        IndicatorType indicatorType = indicatorTypes.get(ThreadLocalRandom.current()
                .nextInt(indicatorTypes.size()));
        TimeFrame timeFrame = timeFrames.get(ThreadLocalRandom.current()
                .nextInt(timeFrames.size()));
        Map<String, String> parameters = randomParametersByIndicatorType.getRandomParametersByIndicatorType(
                indicatorType);

        CandlesInformation candlesInformation = candlesInformationService.save(
                CandlesInformation.builder().timeFrame(timeFrame).currencyPair(currencyPair).build());


        Indicator indicator = Indicator.builder()
                .indicatorType(indicatorType)
                .candlesInformation(candlesInformation)
                .parameters(parameters)
                .build();

        return informationOfIndicatorService.save(indicator);
    }
}
