package com.finance.check.strategy.strategyPreparation;

import com.finance.check.strategy.StrategyExecutor;
import com.finance.check.strategy.checker.MacroClosingDealchecker;
import com.finance.check.strategy.dealManagement.closingDealManagement.ClosingDealManager;
import com.finance.check.strategy.dealManagement.openingDealManagement.OpeningDealManager;
import com.finance.check.strategy.dealManagement.updatingDealManagement.UpdatingDealManager;
import com.finance.dataHolder.DataOfStrategy;
import com.finance.dataHolder.StatisticsDataOfStrategy;
import org.springframework.beans.factory.annotation.Qualifier;

public class StrategyExecutorConfigurationImpl implements StrategyExecutorConfiguration {

    @Qualifier(value = "macroClosingDealcheckerImpl")
    MacroClosingDealchecker macroClosingDealchecker;
    ClosingDealManager closingDealManager;
    UpdatingDealManager updatingDealManager;
    OpeningDealManager openingDealManager;

    public StrategyExecutor configurate(DataOfStrategy readyDataOfStrategy,
                                        StatisticsDataOfStrategy statisticsDataOfStrategy) {

        return new StrategyExecutor(readyDataOfStrategy, statisticsDataOfStrategy,
                macroClosingDealchecker, closingDealManager, updatingDealManager, openingDealManager);
    }
}
