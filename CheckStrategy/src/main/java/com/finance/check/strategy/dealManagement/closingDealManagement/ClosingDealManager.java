package com.finance.check.strategy.dealManagement.closingDealManagement;

import com.finance.dataHolder.DataOfDeal;
import com.finance.dataHolder.DataOfStrategy;
import com.finance.dataHolder.StatisticsDataOfStrategy;

public interface ClosingDealManager {

    void execute(DataOfStrategy dataOfStrategy, int cursor, DataOfDeal dataOfDeal,
                 StatisticsDataOfStrategy statisticsDataOfStrategy);

}
