package com.finance.strategyGeneration.model;

import com.finance.strategyDescriptionParameters.CandlesInformation;
import com.finance.strategyDescriptionParameters.indicators.IndicatorType;

import java.util.Map;

public class InformationOfIndicator {

    long id;
    IndicatorType indicatorType;
    CandlesInformation candlesInformation;
    Map<String, String> parameters;
}
