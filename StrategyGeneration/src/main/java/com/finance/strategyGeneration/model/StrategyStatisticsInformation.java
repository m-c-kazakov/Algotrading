package com.finance.strategyGeneration.model;


import lombok.Value;

@Value(staticConstructor = "of")
public class StrategyStatisticsInformation {

    Long strategyId;
    long score;

    int valueOfAcceptableRisk;

    long maximumPercentDrawdownFromStartScore;
    long averagePercentDrawdownOfScore;
    long maximumValueFromScore;

    Integer numberOfWinningDeals;
    Integer numberOfLosingDeals;
}
