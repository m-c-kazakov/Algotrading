package com.finance.check.strategy.strategyPreparation;

import com.finance.check.strategy.service.StrategyExecutor;
import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.dataHolder.StatisticsDataOfStrategy;

public interface StrategyExecutorConfiguration {

    StrategyExecutor configurate(DescriptionOfStrategy readyDescriptionOfStrategy,
                                 StatisticsDataOfStrategy statisticsDataOfStrategy);
}
