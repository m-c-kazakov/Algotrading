package com.finance.strategyGeneration.service;

import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.TimeFrame;
import com.finance.strategyGeneration.model.InformationOfCandles;

public interface InformationOfCandleService {

    InformationOfCandles findById(long candlesInformationId);

    InformationOfCandles create(TimeFrame timeFrame, CurrencyPair currencyPair);

    InformationOfCandles create(InformationOfCandles informationOfCandles);
}
