package com.finance.createIndicatorData.dto;

import com.finance.dataHolder.DataOfCandle;
import com.finance.strategyDescriptionParameters.CandlesInformation;
import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class ResponseDataOfStrategy {

    CandlesInformation candlesInformation;
    List<DataOfCandle> candles;
    List<Byte> decisionToOpenADeal;
    List<Byte> decisionToCloseADeal;
}
