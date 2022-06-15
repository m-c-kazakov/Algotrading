package com.finance.check.strategy.dealManagement.takeProfitManagement;

import com.finance.check.strategy.dataHolder.DataOfDeal;
import com.finance.check.strategy.dataHolder.DataOfStrategy;

public interface TakeProfitManager {

    void create(DataOfStrategy dataOfStrategy, DataOfDeal dataOfDeal, int cursor);
}
