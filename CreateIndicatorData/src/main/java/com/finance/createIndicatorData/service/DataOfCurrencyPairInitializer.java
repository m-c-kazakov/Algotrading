package com.finance.createIndicatorData.service;

import com.finance.createIndicatorData.dto.RequestDataOfStrategy;
import com.finance.createIndicatorData.model.DataOfCurrencyPair;

import java.util.Set;

public interface DataOfCurrencyPairInitializer {

    Set<DataOfCurrencyPair> execute(RequestDataOfStrategy request);

}
