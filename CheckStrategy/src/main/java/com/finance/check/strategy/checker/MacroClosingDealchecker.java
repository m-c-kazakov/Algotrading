package com.finance.check.strategy.checker;

import com.finance.check.strategy.dataHolder.DataOfDeal;
import com.finance.check.strategy.dataHolder.DataOfStrategy;

public interface MacroClosingDealchecker {

    boolean isNeedClosingDeal(DataOfStrategy dataOfStrategy, int cursor, DataOfDeal dataOfDeal);
}
