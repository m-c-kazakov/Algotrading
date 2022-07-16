package com.finance.utils.dto;

import com.finance.dataHolder.DataOfCandle;
import com.finance.strategyDescriptionParameters.CandlesInformation;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@Builder
@Jacksonized
public class ResponseDataOfStrategy {

    CandlesInformation candlesInformation;
    List<DataOfCandle> candles;
    List<Byte> decisionToOpenADeal;
    List<Byte> decisionToCloseADeal;
}

