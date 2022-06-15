package com.finance.check.strategy.dealManagement.stopLossManagement;

import com.finance.check.strategy.dataHolder.DataOfDeal;
import com.finance.check.strategy.dataHolder.DataOfStrategy;
import com.finance.check.strategy.strategyDescriptionParameters.StopLossConfigurationKey;
import com.finance.check.strategy.strategyDescriptionParameters.TypeOfDeal;
import org.springframework.stereotype.Component;

@Component("FIXED")
public class FixedPipStopLoss implements StopLossManager {
    @Override
    public void create(DataOfStrategy dataOfStrategy, DataOfDeal dataOfDeal, int cursor) {
        //TODO При написании теста отрефакторить. слишком много if
        dataOfStrategy.getFromStopLossConfigurationData(StopLossConfigurationKey.FIXED)
                .map(int.class::cast)
                .ifPresent(fixedStopLoss -> {
                    int stopLoss;
                    TypeOfDeal typeOfDeal = dataOfStrategy.getTypeOfDeal();
                    if (typeOfDeal == TypeOfDeal.BUY) {
                        stopLoss = dataOfStrategy.getClosingPrice(cursor) - fixedStopLoss;
                    } else if (typeOfDeal == TypeOfDeal.SELL) {
                        stopLoss = dataOfStrategy.getClosingPrice(cursor) + fixedStopLoss;
                    } else {
                        throw new RuntimeException("Не определено значение переменной TypeOfDeal == " + typeOfDeal);
                    }
                    dataOfDeal.setStopLoss(stopLoss);
                });
    }
}
