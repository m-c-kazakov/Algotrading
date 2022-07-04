package com.finance.check.strategy.checker;

import com.finance.dataHolder.DataOfDeal;
import com.finance.dataHolder.DescriptionOfStrategy;

public interface MacroClosingDealchecker {

    boolean isNeedClosingDeal(DescriptionOfStrategy descriptionOfStrategy, int cursor, DataOfDeal dataOfDeal);
}
