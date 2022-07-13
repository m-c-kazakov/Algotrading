package com.finance.strategyGeneration.dto;

import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.TimeFrame;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class InformationOfCandlesDto {

    CurrencyPair currencyPair;
    TimeFrame timeFrame;
}
