package com.finance.check.strategy.dealManagement.sumOfDealManagement;

import com.finance.check.strategy.dataHolder.DataOfDeal;
import com.finance.check.strategy.dataHolder.DataOfStrategy;

public interface SumOfDealManger {

    void determineSumOfDeal(DataOfStrategy dataOfStrategy, DataOfDeal dataOfDeal, int cursor, long score);
}
