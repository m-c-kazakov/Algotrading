package com.finance.check.strategy.dealManagement.stopLossManagement;

import com.finance.check.strategy.dataHolder.DataOfDeal;
import com.finance.check.strategy.dataHolder.DataOfStrategy;

public interface StopLossManager {

    void create(DataOfStrategy dataOfStrategy, DataOfDeal dataOfDeal, int cursor);
}
