package com.finance.strategyGeneration.strategyDescriptionParameters.indicators;

import com.finance.strategyGeneration.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyGeneration.strategyDescriptionParameters.TimeFrame;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.Map;



@With
@Getter
@Builder
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Indicator {

    IndicatorType indicatorType;
    TimeFrame timeFrame;
    CurrencyPair currencyPair;
    Map<String, String> parameters;

    public Map<String, String> getParameters() {
        return new HashMap<>(parameters);
    }


    public Indicator copy() {
        return new Indicator(indicatorType, timeFrame, currencyPair, getParameters());
    }
}
