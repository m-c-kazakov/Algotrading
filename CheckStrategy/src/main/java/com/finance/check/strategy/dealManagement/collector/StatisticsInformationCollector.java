package com.finance.check.strategy.dealManagement.collector;

import com.finance.check.strategy.dataHolder.StatisticsDataOfStrategy;

public interface StatisticsInformationCollector {

    void toCollect(StatisticsDataOfStrategy statisticsDataOfStrategy, long startScore, long oldStateOfScore,
                   long newStateOfScore);
}
