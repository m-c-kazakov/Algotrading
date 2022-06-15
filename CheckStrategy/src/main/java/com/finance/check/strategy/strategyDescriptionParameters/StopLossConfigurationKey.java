package com.finance.check.strategy.strategyDescriptionParameters;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum StopLossConfigurationKey {

    FIXED("StopLoss будет формироваться на основе значения по этому ключу минус цена закрытия");

    String description;
}
