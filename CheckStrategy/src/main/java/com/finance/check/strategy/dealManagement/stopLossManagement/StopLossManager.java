package com.finance.check.strategy.dealManagement.stopLossManagement;

import com.finance.dataHolder.DataOfDeal;
import com.finance.dataHolder.DescriptionOfStrategy;

public interface StopLossManager {

    void create(DescriptionOfStrategy descriptionOfStrategy, DataOfDeal dataOfDeal, int cursor);
}
