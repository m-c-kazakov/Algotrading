package com.finance.strategyDescriptionParameters;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@EqualsAndHashCode
@With
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CandlesInformation {

    CurrencyPair currencyPair;

    TimeFrame timeFrame;

    public String toString() {
        return "CandlesInformation(currencyPair=" + this.getCurrencyPair() + ", timeFrame=" + this.getTimeFrame() + ")";
    }
}
