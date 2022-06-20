package com.finance.createIndicatorData.service;

import com.finance.createIndicatorData.model.DataOfCurrencyPair;
import com.finance.strategyDescriptionParameters.DescriptionToOpenADeal;
import com.finance.strategyDescriptionParameters.TimeFrame;

import java.util.Map;

public interface DealDecisionService {
    byte[] makeDecisionOnOpeningDeal(TimeFrame theSmallestTimeFrame, DescriptionToOpenADeal descriptionToOpenADeal, Map<String, DataOfCurrencyPair> dataOfCurrencyPairMap);
}
