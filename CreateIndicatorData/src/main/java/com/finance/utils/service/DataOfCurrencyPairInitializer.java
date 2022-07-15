package com.finance.utils.service;

import com.finance.strategyDescriptionParameters.CandlesInformation;
import com.finance.utils.dto.RequestDataOfStrategy;

import java.util.Set;

public interface DataOfCurrencyPairInitializer {

    Set<CandlesInformation> execute(RequestDataOfStrategy request);

}
