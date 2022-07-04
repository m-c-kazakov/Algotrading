package com.finance.strategyDescriptionParameters;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

@With
@Jacksonized
@Value
@Builder
public class CandlesInformation {

    CurrencyPair currencyPair;

    TimeFrame timeFrame;

    public String toString() {
        return "CandlesInformation(currencyPair=" + this.getCurrencyPair() + ", timeFrame=" + this.getTimeFrame() + ")";
    }
}
