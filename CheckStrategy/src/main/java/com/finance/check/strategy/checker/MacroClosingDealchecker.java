package com.finance.check.strategy.checker;

import com.finance.dataHolder.DataOfDeal;
import com.finance.dataHolder.DataOfStrategy;

public interface MacroClosingDealchecker {

    boolean isNeedClosingDeal(DataOfStrategy dataOfStrategy, int cursor, DataOfDeal dataOfDeal);
}
