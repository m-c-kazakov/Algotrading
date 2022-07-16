package com.finance.dataHolder;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class StatisticsDataOfStrategy {

    @NonNull
    Long specificationOfStrategyId;
    @Setter
    @NonNull
    Long score;
    @NonNull
    Integer valueOfAcceptableRisk;

    @Setter
    @NonNull
    Long maximumPercentDrawdownFromStartScore;
    @Setter
    @NonNull
    Long averagePercentDrawdownOfScore;
    @Setter
    @NonNull
    Long maximumValueFromScore;

    @NonNull
    Integer numberOfWinningDeals;
    @NonNull
    Integer numberOfLosingDeals;


    public void incrementLosingDeal() {
        this.numberOfLosingDeals++;
    }

    public void incrementWinningDeal() {
        this.numberOfWinningDeals++;
    }

    public boolean isNeedToBreakStrategy() {
        return this.score <= this.valueOfAcceptableRisk;
    }

}
