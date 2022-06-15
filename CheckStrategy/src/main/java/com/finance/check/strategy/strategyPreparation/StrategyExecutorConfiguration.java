package com.finance.check.strategy.strategyPreparation;

import com.finance.check.strategy.StrategyExecutor;
import com.finance.check.strategy.dataHolder.DataOfStrategy;
import com.finance.check.strategy.dataHolder.StatisticsDataOfStrategy;

public interface StrategyExecutorConfiguration {

    StrategyExecutor configurate(DataOfStrategy readyDataOfStrategy,
                                 StatisticsDataOfStrategy statisticsDataOfStrategy);
}
