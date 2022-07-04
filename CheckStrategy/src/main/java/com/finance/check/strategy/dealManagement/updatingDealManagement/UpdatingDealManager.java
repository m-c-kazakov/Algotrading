package com.finance.check.strategy.dealManagement.updatingDealManagement;


import com.finance.dataHolder.DataOfDeal;
import com.finance.dataHolder.DescriptionOfStrategy;

public interface UpdatingDealManager {

    void update(DescriptionOfStrategy descriptionOfStrategy, DataOfDeal dataOfDeal, int cursor);
}
