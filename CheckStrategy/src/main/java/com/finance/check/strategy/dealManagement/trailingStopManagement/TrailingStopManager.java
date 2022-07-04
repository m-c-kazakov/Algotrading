package com.finance.check.strategy.dealManagement.trailingStopManagement;

import com.finance.dataHolder.DataOfDeal;
import com.finance.dataHolder.DescriptionOfStrategy;

public interface TrailingStopManager {

    void update(DescriptionOfStrategy descriptionOfStrategy, DataOfDeal dataOfDeal, int cursor);
}
