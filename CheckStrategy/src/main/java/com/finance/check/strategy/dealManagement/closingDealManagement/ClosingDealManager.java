package com.finance.check.strategy.dealManagement.closingDealManagement;

import com.finance.dataHolder.DataOfDeal;
import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.dataHolder.StatisticsDataOfStrategy;

public interface ClosingDealManager {

    void execute(DescriptionOfStrategy descriptionOfStrategy, int cursor, DataOfDeal dataOfDeal,
                 StatisticsDataOfStrategy statisticsDataOfStrategy);

}
