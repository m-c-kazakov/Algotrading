package com.finance.strategyDescriptionParameters;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

@With
@Value
@Builder
@Jacksonized
@EqualsAndHashCode
public class CandlesInformation {

    Long id;

    CurrencyPair currencyPair;

    TimeFrame timeFrame;

    public int getPer() {
        return timeFrame.getPer();
    }

    public String toString() {
        return "CandlesInformation(currencyPair=" + this.getCurrencyPair() + ", timeFrame=" + this.getTimeFrame() + ")";
    }
}
