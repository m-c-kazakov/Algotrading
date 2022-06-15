package com.finance.strategyGeneration.random.indicator;

import com.finance.strategyGeneration.strategyDescriptionParameters.indicators.IndicatorType;

import java.util.Map;

public interface RandomIndicatorParameters {
    Map<String, String> getRandomParametersByIndicatorType(IndicatorType indicatorType);
}
