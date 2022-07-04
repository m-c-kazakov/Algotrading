package com.finance.check.strategy.strategyPreparation;

import com.finance.check.strategy.service.StrategyExecutor;
import com.finance.dataHolder.DescriptionOfStrategy;

public interface StrategyPreparationManager {
    StrategyExecutor prepare(DescriptionOfStrategy descriptionOfStrategy);
}
