package com.finance.createIndicatorData.dto;

import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class ResponseDataOfStrategy {

    DataOfCurrencyPair dataOfCurrencyPair;
    List<Byte> decisionToOpenADeal;
    List<Byte> decisionToCloseADeal;
}
