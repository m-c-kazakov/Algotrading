package com.finance.strategyGeneration.service;

import com.finance.strategyDescriptionParameters.CandlesInformation;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import com.finance.strategyDescriptionParameters.indicators.IndicatorType;

import java.util.Map;

public interface InformationOfIndicatorService {

    Indicator findById(long id);

    Indicator save(Indicator indicator);

    Indicator save(IndicatorType indicatorType, CandlesInformation candlesInformation, Map<String, String> parameters);
}
