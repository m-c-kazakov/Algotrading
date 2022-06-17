package com.finance.strategyDescriptionParameters.indicators;

import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.TimeFrame;
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
