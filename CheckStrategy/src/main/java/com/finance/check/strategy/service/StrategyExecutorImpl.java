package com.finance.check.strategy.service;

import com.finance.check.strategy.checker.MacroClosingDealchecker;
import com.finance.check.strategy.dealManagement.closingDealManagement.ClosingDealManager;
import com.finance.check.strategy.dealManagement.openingDealManagement.OpeningDealManager;
import com.finance.check.strategy.dealManagement.updatingDealManagement.UpdatingDealManager;
import com.finance.dataHolder.DataOfDeal;
import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.dataHolder.StatisticsDataOfStrategy;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StrategyExecutorImpl implements StrategyExecutor {

    DescriptionOfStrategy descriptionOfStrategy;

    StatisticsDataOfStrategy statisticsDataOfStrategy;

    MacroClosingDealchecker macroClosingDealchecker;
    ClosingDealManager closingDealManager;
    UpdatingDealManager updatingDealManager;

    OpeningDealManager openingDealManager;

    public StrategyExecutorImpl(DescriptionOfStrategy descriptionOfStrategy, StatisticsDataOfStrategy statisticsDataOfStrategy,
                                @Qualifier(value = "macroClosingDealchecker") MacroClosingDealchecker macroClosingDealchecker,
                                ClosingDealManager closingDealManager,
                                UpdatingDealManager updatingDealManager, OpeningDealManager openingDealManager) {
        this.descriptionOfStrategy = descriptionOfStrategy;
        this.statisticsDataOfStrategy = statisticsDataOfStrategy;
        this.macroClosingDealchecker = macroClosingDealchecker;
        this.closingDealManager = closingDealManager;
        this.updatingDealManager = updatingDealManager;
        this.openingDealManager = openingDealManager;
    }

    @Override
    public StatisticsDataOfStrategy perform() {

        boolean orderIsOpen = false;

        DataOfDeal dataOfDeal = new DataOfDeal();
        for (int cursor = 0; cursor < descriptionOfStrategy.getDataLength(); cursor++) {

            // TODO можно переделать в конечный автомат, он же паттерн состояние
            if (orderIsOpen) {
                // TODO Сохранить максимально значение по тренду, чтобы понять на сколько вовремя сделка закрылась
                log.debug("Strategy.id={}, score={}", descriptionOfStrategy.getId(), statisticsDataOfStrategy.getScore());
                if (macroClosingDealchecker.isNeedClosingDeal(descriptionOfStrategy, cursor, dataOfDeal)) {
                    closingDealManager.execute(descriptionOfStrategy, cursor, dataOfDeal, statisticsDataOfStrategy);
                    if (statisticsDataOfStrategy.isNeedToBreakStrategy()) {
                        break;
                    }
                    dataOfDeal = null;
                    orderIsOpen = false;
                } else {
                    // todo записать максимальное расстояние от цены открытия сделки
                    // нужно для того чтобы при закрытии сделки посчитать сколько могли бы заработать если бы закрыли раньше
                    // т.е. измерить эффективность закрытия сделки

                    updatingDealManager.update(descriptionOfStrategy, dataOfDeal, cursor);
                }
            } else {

                if (descriptionOfStrategy.getDecisionToOpenADeal(cursor)) {
                    dataOfDeal = openingDealManager.open(descriptionOfStrategy, statisticsDataOfStrategy, cursor);
                    orderIsOpen = true;
                }

            }
        }

        return statisticsDataOfStrategy;
    }
}
