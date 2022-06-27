package com.finance.createIndicatorData.service.converterToDataOfIndicator;

import com.finance.createIndicatorData.dto.DataOfCurrencyPair;
import com.finance.createIndicatorData.model.DataOfIndicator;
import com.finance.createIndicatorData.service.converterToDataOfIndicator.indicators.SMAIndicator;
import com.finance.strategyDescriptionParameters.TypeOfDeal;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import com.finance.strategyDescriptionParameters.indicators.SmaParameters;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;


@Component(value = "SMA")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SmaConverter implements ConverterToDataOfIndicator {

    SMAIndicator smaIndicator;
    IndicatorUtils indicatorUtils;

    @Override
    public DataOfIndicator convert(Indicator indicator, TypeOfDeal typeOfDeal, DataOfCurrencyPair dataOfCurrencyPair) {

        int period = Integer.parseInt(indicator.getValueFromParametersByKey(SmaParameters.PERIOD.name()));
        List<Integer> dataOfCandle = dataOfCurrencyPair.getDataByTypeOfBar(
                indicator.getValueFromParametersByKey(SmaParameters.CALCULATE_BY.name()));

        // Получить массив с данными индикатора
        List<Integer> smaResult = smaIndicator.sequentialGenerationSmaResult(period, dataOfCandle);

        // обрезать массив dataOfCandle до размера smaResult
        List<Integer> trimDataOfCandle = indicatorUtils.trimTheArray(period, dataOfCandle);

        // Сформировать коллекцию решений
        List<Integer> decision = smaIndicator.getDecision(typeOfDeal, indicator, trimDataOfCandle, smaResult);

        // Сформировать объект с результатом
        return DataOfIndicator.builder()
                .decisionByDeal(decision)
                .indicatorType(indicator.getIndicatorType())
                .currencyPair(dataOfCurrencyPair.getCurrencyPair())
                .timeFrame(indicator.getTimeFrame())
                .build();
    }


}
