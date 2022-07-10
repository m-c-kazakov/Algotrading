package com.finance.strategyGeneration.service;

import com.finance.strategyGeneration.model.InformationOfIndicator;

import java.util.List;

public interface InformationOfIndicatorService {

    InformationOfIndicator findById(long id);

    InformationOfIndicator create(InformationOfIndicator informationOfIndicator);

    List<InformationOfIndicator> findAllById(List<String> descriptionToOpenADeal);
}
