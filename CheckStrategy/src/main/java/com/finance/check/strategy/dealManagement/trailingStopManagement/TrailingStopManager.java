package com.finance.check.strategy.dealManagement.trailingStopManagement;

import com.finance.dataHolder.DataOfDeal;
import com.finance.dataHolder.DataOfStrategy;

public interface TrailingStopManager {

    void update(DataOfStrategy dataOfStrategy, DataOfDeal dataOfDeal, int cursor);
}
