package com.finance.strategyGeneration.service;

import com.finance.strategyDescriptionParameters.indicators.Indicator;

public interface InformationOfIndicatorService {

    Indicator findById(Long id);

    Indicator save(Indicator indicator);
}
