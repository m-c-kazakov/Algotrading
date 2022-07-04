package com.finance.createIndicatorData.service;

import com.finance.createIndicatorData.dto.DataOfCurrencyPair;
import com.finance.strategyDescriptionParameters.CandlesInformation;

public interface DataOfCurrencyPairService {


    DataOfCurrencyPair getCurrencyPair(CandlesInformation candlesInformation);
}
