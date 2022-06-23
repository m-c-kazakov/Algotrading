package com.finance.createIndicatorData.service;

import com.finance.createIndicatorData.dto.RequestDataOfStrategy;
import com.finance.createIndicatorData.model.DataOfCurrencyPair;

import java.util.Map;

public interface DealDecisionService {
    Byte[] makeDecisionOnOpeningDeal(RequestDataOfStrategy request,
                                     Map<String, DataOfCurrencyPair> dataOfCurrencyPairMap);
}
