package com.finance.strategyGeneration.strategyDescriptionParameters;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CandlesInformation {

    CurrencyPair currencyPair;

    TimeFrame timeFrame;
}
