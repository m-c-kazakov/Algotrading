package com.finance.check.strategy.dealManagement.openingDealManagement;

import com.finance.dataHolder.DataOfDeal;
import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.dataHolder.StatisticsDataOfStrategy;

public interface OpeningDealManager {

    DataOfDeal open(DescriptionOfStrategy descriptionOfStrategy, StatisticsDataOfStrategy statisticsDataOfStrategy, int cursor);
}
