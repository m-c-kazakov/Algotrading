package com.finance.strategyDescriptionParameters;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum StopLossConfigurationKey {

    FIXED_STOP_LOSS("StopLoss будет формироваться на основе значения по этому ключу +/- цена закрытия");

    String description;
}
