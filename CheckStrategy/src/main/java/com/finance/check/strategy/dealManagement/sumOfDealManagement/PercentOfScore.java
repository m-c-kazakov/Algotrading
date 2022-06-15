package com.finance.check.strategy.dealManagement.sumOfDealManagement;

import com.finance.check.strategy.dataHolder.DataOfDeal;
import com.finance.check.strategy.dataHolder.DataOfStrategy;
import com.finance.check.strategy.strategyDescriptionParameters.SumOfDealConfigurationKey;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("PERCENT_OF_SCORE")
public class PercentOfScore implements SumOfDealManger {

    @Override
    public void determineSumOfDeal(DataOfStrategy dataOfStrategy, DataOfDeal dataOfDeal, int cursor, long score) {


        Optional.ofNullable(
                        dataOfStrategy.getFromSumOfDealConfigurationData(SumOfDealConfigurationKey.PERCENT_OF_SCORE))
                .map(int.class::cast)
                .ifPresent(percentOfScore -> {
                    // TODO при тестировании отрефакторить. Вынести функцию получения суммы сделки в отдельный метод и отдельно протестировать.
                    long sumOfDeal = score * percentOfScore / 100;
                    dataOfDeal.setSumOfDeal(sumOfDeal);
                });


    }
}
