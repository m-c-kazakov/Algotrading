package com.finance.utils.service.converterToDataOfIndicator;

import com.finance.strategyDescriptionParameters.TypeOfDeal;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import com.finance.strategyDescriptionParameters.indicators.SmaParameters;
import com.finance.utils.dto.DataOfCurrencyPair;
import com.finance.utils.model.DataOfIndicator;
import com.finance.utils.service.converterToDataOfIndicator.indicators.SMAIndicator;
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

        // Привести размер коллекции smaResult к начальному размеру коллекции данных свечей
        List<Integer> increaseIndicatorResultSize =
                indicatorUtils.increaseIndicatorResultSize(smaResult, dataOfCandle.size());

        // Сформировать коллекцию решений
        List<Integer> decision =
                smaIndicator.getDecision(typeOfDeal, indicator, dataOfCandle, increaseIndicatorResultSize);


        // Сформировать объект с результатом
        return DataOfIndicator.builder()
                .typeOfDeal(typeOfDeal)
                .decisionByDeal(decision)
                .indicatorType(indicator.getIndicatorType())
                .currencyPair(dataOfCurrencyPair.getCurrencyPair())
                .timeFrame(indicator.getTimeFrame())
                .build();
    }


}
