package com.finance.check.strategy.strategyPreparation;

import com.finance.check.strategy.service.StrategyExecutorImpl;
import com.finance.dataHolder.DescriptionOfStrategy;

public interface StrategyPreparationManager {
    StrategyExecutorImpl prepare(DescriptionOfStrategy descriptionOfStrategy);
}
