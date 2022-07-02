package com.finance.createIndicatorData.dto;

import lombok.Value;

@Value(staticConstructor = "of")
public class ResponseDataOfStrategy {

    DataOfCurrencyPair dataOfCurrencyPair;
    Byte[] decisionToOpenADeal;
    Byte[] decisionToCloseADeal;
}
