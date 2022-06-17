package com.finance.check.strategy.dealManagement.updatingDealManagement;


import com.finance.dataHolder.DataOfDeal;
import com.finance.dataHolder.DataOfStrategy;

public interface UpdatingDealManager {

    void update(DataOfStrategy dataOfStrategy, DataOfDeal dataOfDeal, int cursor);
}
