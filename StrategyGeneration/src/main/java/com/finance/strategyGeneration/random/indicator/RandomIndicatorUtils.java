package com.finance.strategyGeneration.random.indicator;

import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.indicators.Indicator;

public interface RandomIndicatorUtils {
    Indicator getRandomIndicator(CurrencyPair currencyPair);
}
