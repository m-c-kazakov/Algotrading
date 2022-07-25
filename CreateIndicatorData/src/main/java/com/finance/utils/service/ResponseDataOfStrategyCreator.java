package com.finance.utils.service;

import com.finance.dataHolder.DataOfCandle;
import com.finance.strategyDescriptionParameters.CandlesInformation;
import com.finance.utils.dto.ResponseDataOfStrategy;

import java.util.List;

public interface ResponseDataOfStrategyCreator {
    ResponseDataOfStrategy execute(List<Byte> decisionOnOpeningDeal, CandlesInformation candlesInformation, List<DataOfCandle> candles);
}
