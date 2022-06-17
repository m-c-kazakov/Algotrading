package com.finance.check.strategy.dealManagement.sumOfDealManagement;

import com.finance.dataHolder.DataOfDeal;
import com.finance.dataHolder.DataOfStrategy;

public interface SumOfDealManger {

    void determineSumOfDeal(DataOfStrategy dataOfStrategy, DataOfDeal dataOfDeal, int cursor, long score);
}
