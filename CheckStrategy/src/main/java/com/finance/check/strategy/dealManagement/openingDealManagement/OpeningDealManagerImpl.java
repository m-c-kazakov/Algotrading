package com.finance.check.strategy.dealManagement.openingDealManagement;

import com.finance.check.strategy.dealManagement.stopLossManagement.StopLossManager;
import com.finance.check.strategy.dealManagement.sumOfDealManagement.SumOfDealManger;
import com.finance.check.strategy.dealManagement.takeProfitManagement.TakeProfitManager;
import com.finance.check.strategy.dealManagement.trailingStopManagement.TrailingStopManager;
import com.finance.dataHolder.DataOfDeal;
import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.dataHolder.StatisticsDataOfStrategy;
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
    public DataOfDeal open(DescriptionOfStrategy descriptionOfStrategy, StatisticsDataOfStrategy statisticsDataOfStrategy,
                           int cursor) {

        DataOfDeal dataOfDeal = new DataOfDeal();

        dataOfDeal.setOpeningPrice(descriptionOfStrategy.getClosingPrice(cursor));

        ofNullable(trailingStopManagement.get(descriptionOfStrategy.getTrailingStopType()
                .name()))
                .ifPresent(
                        trailingStopManager -> trailingStopManager.update(descriptionOfStrategy, dataOfDeal, cursor));

        ofNullable(sumOfDealManagement.get(descriptionOfStrategy.getSumOfDealType()
                .name()))
                .ifPresent(
                        sumOfDealManger -> sumOfDealManger.determineSumOfDeal(descriptionOfStrategy, dataOfDeal, cursor,
                                statisticsDataOfStrategy.getScore()));

        ofNullable(stopLossManagement.get(descriptionOfStrategy.getStopLossType()
                .name()))
                .ifPresent(stopLossManager -> stopLossManager.create(descriptionOfStrategy, dataOfDeal, cursor));

        ofNullable(takeProfitManagement.get(descriptionOfStrategy.getTakeProfitType()
                .name()))
                .ifPresent(takeProfitManager -> takeProfitManager.create(descriptionOfStrategy, dataOfDeal, cursor));
        return dataOfDeal;
    }
}
