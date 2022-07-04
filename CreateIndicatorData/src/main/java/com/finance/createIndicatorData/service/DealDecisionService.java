package com.finance.createIndicatorData.service;

import com.finance.createIndicatorData.dto.DataOfCurrencyPair;
import com.finance.createIndicatorData.dto.RequestDataOfStrategy;

import java.util.List;
import java.util.Map;

public interface DealDecisionService {
    List<Byte> makeDecisionOnOpeningDeal(RequestDataOfStrategy request,
                                         Map<String, DataOfCurrencyPair> dataOfCurrencyPairMap);
}
