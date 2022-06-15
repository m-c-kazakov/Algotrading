package com.finance.strategyGeneration.strategyDescriptionParameters.indicators;

import com.finance.strategyGeneration.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyGeneration.strategyDescriptionParameters.TimeFrame;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Indicator {

    IndicatorType indicatorType;
    TimeFrame timeFrame;
    CurrencyPair currencyPair;
    Map<String, String> parameters;
}
