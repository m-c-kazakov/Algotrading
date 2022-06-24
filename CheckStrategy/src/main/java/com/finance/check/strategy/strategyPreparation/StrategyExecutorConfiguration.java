package com.finance.check.strategy.strategyPreparation;

import com.finance.check.strategy.service.StrategyExecutor;
import com.finance.dataHolder.DataOfStrategy;
import com.finance.dataHolder.StatisticsDataOfStrategy;

public interface StrategyExecutorConfiguration {

    StrategyExecutor configurate(DataOfStrategy readyDataOfStrategy,
                                 StatisticsDataOfStrategy statisticsDataOfStrategy);
}
