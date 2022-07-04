package com.finance.strategyGeneration.service;

import com.finance.dataHolder.StatisticsDataOfStrategy;
import com.finance.strategyGeneration.model.StrategyStatisticsInformation;
import org.springframework.stereotype.Component;

@Component
public class StrategyStatisticsInformationMapperImpl implements StrategyStatisticsInformationMapper {

    @Override
    public StrategyStatisticsInformation mapTo(StatisticsDataOfStrategy statisticsDataOfStrategy) {
        return StrategyStatisticsInformation.of(
                statisticsDataOfStrategy.getStrategyId(),
                statisticsDataOfStrategy.getScore(),
                statisticsDataOfStrategy.getValueOfAcceptableRisk(),
                statisticsDataOfStrategy.getMaximumPercentDrawdownFromStartScore(),
                statisticsDataOfStrategy.getAveragePercentDrawdownOfScore(),
                statisticsDataOfStrategy.getMaximumValueFromScore(),
                statisticsDataOfStrategy.getNumberOfWinningDeals(),
                statisticsDataOfStrategy.getNumberOfLosingDeals()
        );
    }
}
