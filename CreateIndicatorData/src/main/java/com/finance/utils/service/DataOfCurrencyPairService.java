package com.finance.utils.service;

import com.finance.strategyDescriptionParameters.CandlesInformation;
import com.finance.utils.dto.DataOfCurrencyPair;

public interface DataOfCurrencyPairService {


    DataOfCurrencyPair getCurrencyPair(CandlesInformation candlesInformation);
}
