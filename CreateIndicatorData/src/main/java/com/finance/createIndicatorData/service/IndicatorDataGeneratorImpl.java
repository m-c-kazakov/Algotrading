package com.finance.createIndicatorData.service;

import com.finance.createIndicatorData.dao.DataOfIndicatorDao;
import com.finance.createIndicatorData.model.DataOfCurrencyPair;
import com.finance.createIndicatorData.model.DataOfIndicator;
import com.finance.createIndicatorData.service.converterToDataOfIndicator.ConverterToDataOfIndicator;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IndicatorDataGeneratorImpl implements IndicatorDataGenerator {

    Map<String, ConverterToDataOfIndicator> converterToDataOfIndicatorMap;

    DataOfIndicatorDao dataOfIndicatorDao;

    @Override
    public DataOfIndicator generate(Indicator indicator, DataOfCurrencyPair dataOfCurrencyPair) {

        // поискать данные в БД
        // если данных нет то
        // По типу нужно найти обработчик данных по индикатору
        // вызвать обработчик и получить DataOfIndicator

        return dataOfIndicatorDao.getDataOfIndicator(indicator.candlesInformationToString())
                .orElseGet(() -> {
                    ConverterToDataOfIndicator converterToDataOfIndicator = converterToDataOfIndicatorMap.get(
                            indicator.getIndicatorType().name());
                    return converterToDataOfIndicator.convert(indicator, dataOfCurrencyPair);
                });



    }
}
