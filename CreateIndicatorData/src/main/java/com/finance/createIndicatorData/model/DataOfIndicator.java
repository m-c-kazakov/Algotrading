package com.finance.createIndicatorData.model;


import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.TimeFrame;
import com.finance.strategyDescriptionParameters.indicators.IndicatorType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataOfIndicator {
    // TODO данные хранятся как последовательность чисел.
    int[] data;

    IndicatorType indicatorType;

    CurrencyPair currencyPair;

    TimeFrame timeFrame;



}
