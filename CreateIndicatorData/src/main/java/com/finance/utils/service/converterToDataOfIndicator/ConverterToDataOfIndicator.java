package com.finance.utils.service.converterToDataOfIndicator;

import com.finance.strategyDescriptionParameters.TypeOfDeal;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import com.finance.utils.dto.DataOfCurrencyPair;
import com.finance.utils.model.DataOfIndicator;

public interface ConverterToDataOfIndicator {

    DataOfIndicator convert(Indicator indicator, TypeOfDeal typeOfDeal, DataOfCurrencyPair dataOfCurrencyPair);
}
