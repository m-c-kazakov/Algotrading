package com.finance.check.strategy.dealManagement.openingDealManagement;

import com.finance.dataHolder.DataOfDeal;
import com.finance.dataHolder.DataOfStrategy;
import com.finance.dataHolder.StatisticsDataOfStrategy;

public interface OpeningDealManager {

    DataOfDeal open(DataOfStrategy dataOfStrategy, StatisticsDataOfStrategy statisticsDataOfStrategy, int cursor);
}
