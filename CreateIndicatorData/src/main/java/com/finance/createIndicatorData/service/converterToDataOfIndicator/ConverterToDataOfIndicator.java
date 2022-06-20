package com.finance.createIndicatorData.service.converterToDataOfIndicator;

import com.finance.createIndicatorData.model.DataOfCurrencyPair;
import com.finance.createIndicatorData.model.DataOfIndicator;
import com.finance.strategyDescriptionParameters.indicators.Indicator;

public interface ConverterToDataOfIndicator {

    DataOfIndicator convert(Indicator indicator, DataOfCurrencyPair dataOfCurrencyPair);
}
