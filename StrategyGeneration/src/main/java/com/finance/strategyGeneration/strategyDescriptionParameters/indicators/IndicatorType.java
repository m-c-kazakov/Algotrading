package com.finance.strategyGeneration.strategyDescriptionParameters.indicators;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum IndicatorType {

    SMA("Simple moving average");


    String name;


}
