package com.finance.dataHolder;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
public class DataOfCandle {

    int closingPrices;
    int openingPrices;
    int lowPrices;
    int highPrices;
}
