package com.finance.check.strategy.dealManagement.openingDealManagement;

import com.finance.check.strategy.dataHolder.DataOfDeal;
import com.finance.check.strategy.dataHolder.DataOfStrategy;
import com.finance.check.strategy.dataHolder.StatisticsDataOfStrategy;
import com.finance.check.strategy.dealManagement.stopLossManagement.StopLossManager;
import com.finance.check.strategy.dealManagement.sumOfDealManagement.SumOfDealManger;
import com.finance.check.strategy.dealManagement.takeProfitManagement.TakeProfitManager;
import com.finance.check.strategy.dealManagement.trailingStopManagement.TrailingStopManager;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.util.Optional.ofNullable;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OpeningDealManagerImpl implements OpeningDealManager {

    Map<String, TrailingStopManager> trailingStopManagement;
    Map<String, SumOfDealManger> sumOfDealManagement;
    Map<String, StopLossManager> stopLossManagement;
    Map<String, TakeProfitManager> takeProfitManagement;

    @Override
    public DataOfDeal open(DataOfStrategy dataOfStrategy, StatisticsDataOfStrategy statisticsDataOfStrategy,
                           int cursor) {

        DataOfDeal dataOfDeal = new DataOfDeal();

        dataOfDeal.setOpeningPrice(dataOfStrategy.getClosingPrice(cursor));

        ofNullable(trailingStopManagement.get(dataOfStrategy.getTrailingStopType()
                .name()))
                .ifPresent(trailingStopManager -> trailingStopManager.update(dataOfStrategy, dataOfDeal, cursor));

        ofNullable(sumOfDealManagement.get(dataOfStrategy.getCommandTypeForDeterminingSumOfDeal()
                .name()))
                .ifPresent(sumOfDealManger -> sumOfDealManger.determineSumOfDeal(dataOfStrategy, dataOfDeal, cursor,
                        statisticsDataOfStrategy.getScore()));

        ofNullable(stopLossManagement.get(dataOfStrategy.getStopLossType()
                .name()))
                .ifPresent(stopLossManager -> stopLossManager.create(dataOfStrategy, dataOfDeal, cursor));

        ofNullable(takeProfitManagement.get(dataOfStrategy.getTakeProfitType()
                .name()))
                .ifPresent(takeProfitManager -> takeProfitManager.create(dataOfStrategy, dataOfDeal, cursor));
        return dataOfDeal;
    }
}
