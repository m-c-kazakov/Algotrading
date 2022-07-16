package com.finance.utils;


import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class StatisticsDataOfStrategyDto {

    Long specificationOfStrategyId;
    Long score;
    Integer valueOfAcceptableRisk;
    Long maximumPercentDrawdownFromStartScore;
    Long averagePercentDrawdownOfScore;
    Long maximumValueFromScore;
    Integer numberOfWinningDeals;
    Integer numberOfLosingDeals;
}
