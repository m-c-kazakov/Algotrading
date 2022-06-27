package com.finance.createIndicatorData.model;

import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.TimeFrame;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Candle {

    Long id;
    CurrencyPair currencyPair;
    TimeFrame timeFrame;
    BigDecimal openPrice;
    BigDecimal closePrice;
    BigDecimal highPrice;
    BigDecimal lowPrice;
}
