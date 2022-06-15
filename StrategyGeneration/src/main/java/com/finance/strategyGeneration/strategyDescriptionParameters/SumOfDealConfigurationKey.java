package com.finance.strategyGeneration.strategyDescriptionParameters;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum SumOfDealConfigurationKey {

    PERCENT_OF_SCORE("Значение, на которое будет заключается сделка, формируется как процент от значения счета");

    String description;
}
