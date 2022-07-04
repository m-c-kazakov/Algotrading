package com.finance.strategyGeneration.service;

import com.finance.dataHolder.StatisticsDataOfStrategy;
import com.finance.strategyGeneration.model.StrategyStatisticsInformation;

public interface StrategyStatisticsInformationMapper {
    StrategyStatisticsInformation mapTo(StatisticsDataOfStrategy statisticsDataOfStrategy);
}
