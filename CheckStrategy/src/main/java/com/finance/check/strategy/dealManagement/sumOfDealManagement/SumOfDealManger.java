package com.finance.check.strategy.dealManagement.sumOfDealManagement;

import com.finance.dataHolder.DataOfDeal;
import com.finance.dataHolder.DescriptionOfStrategy;

public interface SumOfDealManger {

    void determineSumOfDeal(DescriptionOfStrategy descriptionOfStrategy, DataOfDeal dataOfDeal, int cursor, long score);
}
