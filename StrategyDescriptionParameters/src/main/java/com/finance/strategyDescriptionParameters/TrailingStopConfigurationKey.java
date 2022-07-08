package com.finance.strategyDescriptionParameters;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum TrailingStopConfigurationKey {

    FIXED_TRAILING_STOP("TrailingStop будет формироваться на основе значения по этому ключу минус цена закрытия");

    String description;
}
