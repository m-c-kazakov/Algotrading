package com.finance.check.strategy.dataHolder;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatisticsDataOfStrategy {

    Long strategyId;
    @Setter
    long score;

    int valueOfAcceptableRisk;

    @Setter
    long maximumPercentDrawdownFromStartScore;
    @Setter
    long averagePercentDrawdownOfScore;
    @Setter
    long maximumValueFromScore;


    Integer numberOfWinningDeals;
    Integer numberOfLosingDeals;


    public void incrementLosingDeal() {
        this.numberOfLosingDeals = numberOfLosingDeals + 1;
    }

    public void incrementWinningDeal() {
        this.numberOfWinningDeals = numberOfWinningDeals + 1;
    }

    public boolean isNeedToBreakStrategy() {
        return score <= valueOfAcceptableRisk;
    }

}
