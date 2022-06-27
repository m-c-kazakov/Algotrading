package com.finance.createIndicatorData.service;

import com.finance.createIndicatorData.dto.DataOfCurrencyPair;
import com.finance.createIndicatorData.model.DataOfIndicator;
import com.finance.strategyDescriptionParameters.TypeOfDeal;
import com.finance.strategyDescriptionParameters.indicators.Indicator;

public interface IndicatorDataGenerator {
    DataOfIndicator generate(Indicator indicator, TypeOfDeal typeOfDeal, DataOfCurrencyPair dataOfCurrencyPair);
}
