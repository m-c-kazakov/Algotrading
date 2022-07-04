package com.finance.check.strategy.dealManagement.takeProfitManagement;

import com.finance.dataHolder.DataOfDeal;
import com.finance.dataHolder.DescriptionOfStrategy;

public interface TakeProfitManager {

    void create(DescriptionOfStrategy descriptionOfStrategy, DataOfDeal dataOfDeal, int cursor);
}
