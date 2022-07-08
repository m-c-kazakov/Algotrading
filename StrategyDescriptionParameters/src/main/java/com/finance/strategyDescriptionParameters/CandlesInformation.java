package com.finance.strategyDescriptionParameters;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;
import org.springframework.lang.NonNull;

@With
@Value
@Builder
@Jacksonized
@EqualsAndHashCode
public class CandlesInformation {

    @EqualsAndHashCode.Exclude
    @NonNull
    Long id;
    @NonNull
    CurrencyPair currencyPair;
    @NonNull
    TimeFrame timeFrame;

    public int getPer() {
        return timeFrame.getPer();
    }

    public String toString() {
        return "CandlesInformation(currencyPair=" + this.getCurrencyPair() + ", timeFrame=" + this.getTimeFrame() + ")";
    }
}
