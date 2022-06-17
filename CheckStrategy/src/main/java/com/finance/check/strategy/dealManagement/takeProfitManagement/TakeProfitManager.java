package com.finance.check.strategy.dealManagement.takeProfitManagement;

import com.finance.dataHolder.DataOfDeal;
import com.finance.dataHolder.DataOfStrategy;

public interface TakeProfitManager {

    void create(DataOfStrategy dataOfStrategy, DataOfDeal dataOfDeal, int cursor);
}
