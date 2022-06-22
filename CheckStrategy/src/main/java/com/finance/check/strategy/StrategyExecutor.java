package com.finance.check.strategy;

import com.finance.check.strategy.checker.MacroClosingDealchecker;
import com.finance.check.strategy.dealManagement.closingDealManagement.ClosingDealManager;
import com.finance.check.strategy.dealManagement.openingDealManagement.OpeningDealManager;
import com.finance.check.strategy.dealManagement.updatingDealManagement.UpdatingDealManager;
import com.finance.dataHolder.DataOfDeal;
import com.finance.dataHolder.DataOfStrategy;
import com.finance.dataHolder.StatisticsDataOfStrategy;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StrategyExecutor implements Runnable {

    DataOfStrategy dataOfStrategy;

    StatisticsDataOfStrategy statisticsDataOfStrategy;

    MacroClosingDealchecker macroClosingDealchecker;
    ClosingDealManager closingDealManager;
    UpdatingDealManager updatingDealManager;

    OpeningDealManager openingDealManager;

    public StrategyExecutor(DataOfStrategy dataOfStrategy, StatisticsDataOfStrategy statisticsDataOfStrategy,
                            @Qualifier(value = "macroClosingDealcheckerImpl") MacroClosingDealchecker macroClosingDealchecker,
                            ClosingDealManager closingDealManager,
                            UpdatingDealManager updatingDealManager, OpeningDealManager openingDealManager) {
        this.dataOfStrategy = dataOfStrategy;
        this.statisticsDataOfStrategy = statisticsDataOfStrategy;
        this.macroClosingDealchecker = macroClosingDealchecker;
        this.closingDealManager = closingDealManager;
        this.updatingDealManager = updatingDealManager;
        this.openingDealManager = openingDealManager;
    }

    @Override
    public void run() {

        boolean orderIsOpen = false;

        DataOfDeal dataOfDeal = new DataOfDeal();
        for (int cursor = 0; cursor < dataOfStrategy.getDataLength(); cursor++) {

            // TODO можно переделать в конечный автомат, он же паттерн состояние
            if (orderIsOpen) {
                // TODO Сохранить максимально значение по тренду, чтобы понять на сколько вовремя сделка закрылась
                if (macroClosingDealchecker.isNeedClosingDeal(dataOfStrategy, cursor, dataOfDeal)) {
                    closingDealManager.execute(dataOfStrategy, cursor, dataOfDeal, statisticsDataOfStrategy);
                    if (statisticsDataOfStrategy.isNeedToBreakStrategy()) {
                        break;
                    }
                    dataOfDeal = null;
                    orderIsOpen = false;
                } else {
                    // todo записать максимальное расстояние от цены открытия сделки
                    // нужно для того чтобы при закрытии сделки посчитать сколько могли бы заработать если бы закрыли раньше
                    // т.е. измерить эффективность закрытия сделки

                    updatingDealManager.update(dataOfStrategy, dataOfDeal, cursor);
                }
            } else {

                if (dataOfStrategy.getDecisionToOpenADeal(cursor)) {
                    dataOfDeal = openingDealManager.open(dataOfStrategy, statisticsDataOfStrategy, cursor);
                    orderIsOpen = true;
                }

            }
        }
    }
}
