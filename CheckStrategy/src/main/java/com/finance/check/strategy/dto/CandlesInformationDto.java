package com.finance.check.strategy.dto;

import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.TimeFrame;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class CandlesInformationDto {

    CurrencyPair currencyPair;
    TimeFrame timeFrame;
}
