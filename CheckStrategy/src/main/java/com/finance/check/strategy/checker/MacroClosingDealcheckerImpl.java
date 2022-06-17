package com.finance.check.strategy.checker;

import com.finance.dataHolder.DataOfDeal;
import com.finance.dataHolder.DataOfStrategy;
import com.finance.strategyDescriptionParameters.TypeOfDeal;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component(value = "macroClosingDealcheckerImpl")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MacroClosingDealcheckerImpl implements MacroClosingDealchecker {

    MacroClosingDealchecker buyDealchecker;
    MacroClosingDealchecker sellDealchecker;

    public MacroClosingDealcheckerImpl(@Qualifier("buyDealchecker") MacroClosingDealchecker buyDealchecker,
                                       @Qualifier("sellDealchecker") MacroClosingDealchecker sellDealchecker) {
        this.buyDealchecker = buyDealchecker;
        this.sellDealchecker = sellDealchecker;
    }

    @Override
    public boolean isNeedClosingDeal(DataOfStrategy dataOfStrategy, int cursor, DataOfDeal dataOfDeal) {

        if (dataOfStrategy.getDecisionToClosingADeal(cursor)) {
            return true;
        }

        if (dataOfStrategy.getTypeOfDeal() == TypeOfDeal.BUY) {
            return buyDealchecker.isNeedClosingDeal(dataOfStrategy, cursor, dataOfDeal);
        } else if (dataOfStrategy.getTypeOfDeal() == TypeOfDeal.SELL) {
            return sellDealchecker.isNeedClosingDeal(dataOfStrategy, cursor, dataOfDeal);
        } else {
            throw new RuntimeException("Некорректно определен тип TypeOfDeal=" + dataOfStrategy.getTypeOfDeal());
        }

    }
}
