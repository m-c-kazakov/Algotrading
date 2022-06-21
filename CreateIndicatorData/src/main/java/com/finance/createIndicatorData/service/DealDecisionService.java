package com.finance.createIndicatorData.service;

import com.finance.createIndicatorData.RequestDataOfStrategy;
import com.finance.createIndicatorData.model.DataOfCurrencyPair;

import java.util.Map;

public interface DealDecisionService {
    byte[] makeDecisionOnOpeningDeal(RequestDataOfStrategy request,
                                     Map<String, DataOfCurrencyPair> dataOfCurrencyPairMap);
}
