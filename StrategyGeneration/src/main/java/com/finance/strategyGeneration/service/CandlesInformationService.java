package com.finance.strategyGeneration.service;

import com.finance.strategyDescriptionParameters.CandlesInformation;
import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.TimeFrame;

public interface CandlesInformationService {

    CandlesInformation findById(long candlesInformationId);

    CandlesInformation save(TimeFrame timeFrame, CurrencyPair currencyPair);
}
