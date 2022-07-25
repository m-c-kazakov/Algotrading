package com.finance.check.strategy.dealManagement.sumOfDealManagement;

import com.finance.dataHolder.DataOfDeal;
import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyDescriptionParameters.SumOfDealConfigurationKey;
import org.springframework.stereotype.Component;

@Component("PERCENT_OF_SCORE")
public class PercentOfScore implements SumOfDealManger {

    @Override
    public void determineSumOfDeal(DescriptionOfStrategy descriptionOfStrategy, DataOfDeal dataOfDeal, int cursor, long score) {


        descriptionOfStrategy.getFromSumOfDealConfigurationData(SumOfDealConfigurationKey.PERCENT_OF_SCORE)
                .map(Integer::parseInt)
                .ifPresent(percentOfScore -> {
                    // TODO Определять на какое количество лотов можно заключить сделку
                    long sumOfDeal = score * percentOfScore / 100;

                    // Расчет количества лотов, на которые можно открыть сделку
                    long lot = sumOfDeal <= 1000 ? 1 : sumOfDeal / 1000;
                    dataOfDeal.setLot(lot);
                });
    }
}
