package com.finance.utils.service;

import com.finance.utils.dto.DataOfCurrencyPair;
import com.finance.utils.dto.RequestDataOfStrategy;

import java.util.List;
import java.util.Map;

public interface DealDecisionService {
    List<Byte> makeDecisionOnOpeningDeal(RequestDataOfStrategy request,
                                         Map<String, DataOfCurrencyPair> dataOfCurrencyPairMap);
}
