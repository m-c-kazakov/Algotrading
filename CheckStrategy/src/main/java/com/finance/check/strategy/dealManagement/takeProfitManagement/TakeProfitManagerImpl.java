package com.finance.check.strategy.dealManagement.takeProfitManagement;

import com.finance.dataHolder.DataOfDeal;
import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyDescriptionParameters.TakeProfitConfigurationKey;
import com.finance.strategyDescriptionParameters.TypeOfDeal;
import org.springframework.stereotype.Component;

@Component("FIXED_TAKE_PROFIT")
public class TakeProfitManagerImpl implements TakeProfitManager {

    @Override
    public void create(DescriptionOfStrategy descriptionOfStrategy, DataOfDeal dataOfDeal, int cursor) {
        // TODO при написании теста отрефакторить. Много вложенных if
        descriptionOfStrategy.getFromTakeProfitConfigurationData(TakeProfitConfigurationKey.FIXED)
                .map(int.class::cast)
                .ifPresent(fixedTakeProfit -> {
                    int takeProfit;
                    TypeOfDeal typeOfDeal = descriptionOfStrategy.getTypeOfDeal();
                    if (typeOfDeal == TypeOfDeal.BUY) {
                        takeProfit = descriptionOfStrategy.getClosingPrice(cursor) + fixedTakeProfit;
                    } else if (typeOfDeal == TypeOfDeal.SELL) {
                        takeProfit = descriptionOfStrategy.getClosingPrice(cursor) - fixedTakeProfit;
                    } else {
                        throw new RuntimeException("Не определено значение переменной TypeOfDeal == " + typeOfDeal);
                    }

                    dataOfDeal.setTakeProfit(takeProfit);
                });
    }
}
