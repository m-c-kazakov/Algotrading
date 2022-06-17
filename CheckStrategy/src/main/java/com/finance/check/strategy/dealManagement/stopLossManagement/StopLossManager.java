package com.finance.check.strategy.dealManagement.stopLossManagement;

import com.finance.dataHolder.DataOfDeal;
import com.finance.dataHolder.DataOfStrategy;

public interface StopLossManager {

    void create(DataOfStrategy dataOfStrategy, DataOfDeal dataOfDeal, int cursor);
}
