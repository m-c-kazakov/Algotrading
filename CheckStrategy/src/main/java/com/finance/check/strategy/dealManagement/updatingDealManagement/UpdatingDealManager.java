package com.finance.check.strategy.dealManagement.updatingDealManagement;

import com.finance.check.strategy.dataHolder.DataOfDeal;
import com.finance.check.strategy.dataHolder.DataOfStrategy;

public interface UpdatingDealManager {

    void update(DataOfStrategy dataOfStrategy, DataOfDeal dataOfDeal, int cursor);
}
