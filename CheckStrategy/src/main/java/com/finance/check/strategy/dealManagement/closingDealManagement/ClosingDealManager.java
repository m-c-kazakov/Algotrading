package com.finance.check.strategy.dealManagement.closingDealManagement;

import com.finance.check.strategy.dataHolder.DataOfDeal;
import com.finance.check.strategy.dataHolder.DataOfStrategy;
import com.finance.check.strategy.dataHolder.StatisticsDataOfStrategy;

public interface ClosingDealManager {

    void execute(DataOfStrategy dataOfStrategy, int cursor, DataOfDeal dataOfDeal,
                 StatisticsDataOfStrategy statisticsDataOfStrategy);

}
