package com.finance.check.strategy.dto;

import com.finance.strategyDescriptionParameters.indicators.IndicatorType;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;

@Value
@Builder
@Jacksonized
public class InformationOfIndicatorDto {


    Long id;
    String hashCode;
    IndicatorType indicatorType;
    InformationOfCandlesDto informationOfCandles;
    Map<String, String> parameters;
}
