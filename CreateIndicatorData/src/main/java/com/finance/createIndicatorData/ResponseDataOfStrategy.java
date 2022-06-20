package com.finance.createIndicatorData;

import com.finance.dataHolder.Candle;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ResponseDataOfStrategy {

    Candle[] candles;
    byte[] decisionToOpenADeal;
    byte[] decisionToCloseADeal;
}
