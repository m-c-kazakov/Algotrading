package com.finance.check.strategy.dealManagement.openingDealManagement;

import com.finance.check.strategy.dataHolder.DataOfDeal;
import com.finance.check.strategy.dataHolder.DataOfStrategy;
import com.finance.check.strategy.dataHolder.StatisticsDataOfStrategy;

public interface OpeningDealManager {

    DataOfDeal open(DataOfStrategy dataOfStrategy, StatisticsDataOfStrategy statisticsDataOfStrategy, int cursor);
}
