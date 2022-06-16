package com.finance.strategyGeneration.strategyDescriptionParameters;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CandlesInformation {

    CurrencyPair currencyPair;

    TimeFrame timeFrame;
}
