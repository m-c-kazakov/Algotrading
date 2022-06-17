package com.finance.strategyDescriptionParameters;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum CurrencyPair {

    // TODO добавить возможность делать стратегии с использованием данных с разных валютных пар, акций и т.д.
    EUR_USD(4);


    int numberOfDigitsAfterTheDecimalPoint;

}
