package com.finance.check.strategy.dealManagement.stopLossManagement;

import com.finance.dataHolder.DataOfDeal;
import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyDescriptionParameters.StopLossConfigurationKey;
import com.finance.strategyDescriptionParameters.TypeOfDeal;
import org.springframework.stereotype.Component;

@Component("FIXED_STOP_LOSS")
public class FixedPipStopLoss implements StopLossManager {
    @Override
    public void create(DescriptionOfStrategy descriptionOfStrategy, DataOfDeal dataOfDeal, int cursor) {
        //TODO При написании теста отрефакторить. слишком много if
        descriptionOfStrategy.getFromStopLossConfigurationData(StopLossConfigurationKey.FIXED_STOP_LOSS)
                .map(int.class::cast)
                .ifPresent(fixedStopLoss -> {
                    int stopLoss;
                    TypeOfDeal typeOfDeal = descriptionOfStrategy.getTypeOfDeal();
                    if (typeOfDeal == TypeOfDeal.BUY) {
                        stopLoss = descriptionOfStrategy.getClosingPrice(cursor) - fixedStopLoss;
                    } else if (typeOfDeal == TypeOfDeal.SELL) {
                        stopLoss = descriptionOfStrategy.getClosingPrice(cursor) + fixedStopLoss;
                    } else {
                        throw new RuntimeException("Не определено значение переменной TypeOfDeal == " + typeOfDeal);
                    }
                    dataOfDeal.setStopLoss(stopLoss);
                });
    }
}
