package com.finance.check.strategy.dealManagement.trailingStopManagement;

import com.finance.check.strategy.dataHolder.DataOfDeal;
import com.finance.check.strategy.dataHolder.DataOfStrategy;

public interface TrailingStopManager {

    void update(DataOfStrategy dataOfStrategy, DataOfDeal dataOfDeal, int cursor);
}
