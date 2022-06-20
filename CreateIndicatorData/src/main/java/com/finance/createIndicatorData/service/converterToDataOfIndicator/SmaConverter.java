package com.finance.createIndicatorData.service.converterToDataOfIndicator;

import com.finance.createIndicatorData.model.DataOfCurrencyPair;
import com.finance.createIndicatorData.model.DataOfIndicator;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;


@Component(value = "SMA")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SmaConverter implements ConverterToDataOfIndicator {



    @Override
    public DataOfIndicator convert(Indicator indicator, DataOfCurrencyPair dataOfCurrencyPair) {

        // сохранить в бд результат есть его нет


        return new DataOfIndicator();
    }
}
