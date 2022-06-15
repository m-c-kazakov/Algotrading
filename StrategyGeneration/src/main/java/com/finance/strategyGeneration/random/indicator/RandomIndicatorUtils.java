package com.finance.strategyGeneration.random.indicator;

import com.finance.strategyGeneration.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyGeneration.strategyDescriptionParameters.indicators.Indicator;

public interface RandomIndicatorUtils {
    Indicator getRandomIndicator(CurrencyPair currencyPair);
}
