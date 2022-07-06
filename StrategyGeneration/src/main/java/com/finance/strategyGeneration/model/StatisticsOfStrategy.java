package com.finance.strategyGeneration.model;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Builder
@Value
public class StatisticsOfStrategy {

    long score;
    int valueOfAcceptableRisk;
    long maximumPercentDrawdownFromStartScore;
    long averagePercentDrawdownOfScore;
    long maximumValueFromScore;
    int numberOfWinningDeals;
    int numberOfLosingDeals;

}
