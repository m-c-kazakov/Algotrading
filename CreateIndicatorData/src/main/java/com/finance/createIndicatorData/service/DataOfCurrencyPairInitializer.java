package com.finance.createIndicatorData.service;

import com.finance.createIndicatorData.dto.RequestDataOfStrategy;
import com.finance.strategyDescriptionParameters.CandlesInformation;

import java.util.Set;

public interface DataOfCurrencyPairInitializer {

    Set<CandlesInformation> execute(RequestDataOfStrategy request);

}
