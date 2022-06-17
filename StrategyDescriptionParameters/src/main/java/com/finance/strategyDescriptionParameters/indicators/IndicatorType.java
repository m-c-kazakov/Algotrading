package com.finance.strategyDescriptionParameters.indicators;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum IndicatorType {

    SMA("Simple moving average", SmaParameters.getParameters());


    String name;
    List<String> namesOfIndicatorParameters;


}
