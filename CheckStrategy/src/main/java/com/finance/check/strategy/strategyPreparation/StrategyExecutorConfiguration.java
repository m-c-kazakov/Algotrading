package com.finance.check.strategy.strategyPreparation;

import com.finance.check.strategy.service.StrategyExecutorImpl;
import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.dataHolder.StatisticsDataOfStrategy;

public interface StrategyExecutorConfiguration {

    StrategyExecutorImpl configurate(DescriptionOfStrategy readyDescriptionOfStrategy,
                                     StatisticsDataOfStrategy statisticsDataOfStrategy);
}
