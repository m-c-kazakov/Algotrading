package com.finance.strategyGeneration.service;

import com.finance.strategyDescriptionParameters.indicators.IndicatorType;
import com.finance.strategyGeneration.model.InformationOfCandles;
import com.finance.strategyGeneration.model.InformationOfIndicator;

import java.util.List;
import java.util.Map;

public interface InformationOfIndicatorService {

    InformationOfIndicator findById(long id);

    List<InformationOfIndicator> findAllById(List<Long> ids);

    InformationOfIndicator create(InformationOfIndicator informationOfIndicator);

    InformationOfIndicator create(IndicatorType indicatorType, InformationOfCandles informationOfCandles, Map<String, String> parameters);

}
