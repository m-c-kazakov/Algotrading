package com.finance.check.strategy.strategyPreparation;

import com.finance.check.strategy.checker.MacroClosingDealchecker;
import com.finance.check.strategy.dealManagement.closingDealManagement.ClosingDealManager;
import com.finance.check.strategy.dealManagement.openingDealManagement.OpeningDealManager;
import com.finance.check.strategy.dealManagement.updatingDealManagement.UpdatingDealManager;
import com.finance.check.strategy.service.StrategyExecutorImpl;
import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.dataHolder.StatisticsDataOfStrategy;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StrategyExecutorConfigurationImpl implements StrategyExecutorConfiguration {

    MacroClosingDealchecker macroClosingDealchecker;
    ClosingDealManager closingDealManager;
    UpdatingDealManager updatingDealManager;
    OpeningDealManager openingDealManager;

    public StrategyExecutorConfigurationImpl(@Qualifier(value = "macroClosingDealchecker") MacroClosingDealchecker macroClosingDealchecker,
                                             ClosingDealManager closingDealManager,
                                             UpdatingDealManager updatingDealManager,
                                             OpeningDealManager openingDealManager) {
        this.macroClosingDealchecker = macroClosingDealchecker;
        this.closingDealManager = closingDealManager;
        this.updatingDealManager = updatingDealManager;
        this.openingDealManager = openingDealManager;
    }

    public StrategyExecutorImpl configurate(DescriptionOfStrategy readyDescriptionOfStrategy,
                                            StatisticsDataOfStrategy statisticsDataOfStrategy) {

        return new StrategyExecutorImpl(readyDescriptionOfStrategy, statisticsDataOfStrategy,
                macroClosingDealchecker, closingDealManager, updatingDealManager, openingDealManager);
    }
}
