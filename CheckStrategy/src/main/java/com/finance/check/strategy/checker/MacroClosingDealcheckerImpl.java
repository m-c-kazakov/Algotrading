package com.finance.check.strategy.checker;

import com.finance.dataHolder.DataOfDeal;
import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyDescriptionParameters.TypeOfDeal;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component(value = "macroClosingDealchecker")
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
    public boolean isNeedClosingDeal(DescriptionOfStrategy descriptionOfStrategy, int cursor, DataOfDeal dataOfDeal) {

        // Todo Добавить в качестве одного из условий закрытия сделки.
        //  Если текущие возможные потери больше чем уровень риска, то закрой сделку.
        if (descriptionOfStrategy.getDecisionToClosingADeal(cursor)) {
            return true;
        }

        if (descriptionOfStrategy.getTypeOfDeal() == TypeOfDeal.BUY) {
            return buyDealchecker.isNeedClosingDeal(descriptionOfStrategy, cursor, dataOfDeal);
        } else if (descriptionOfStrategy.getTypeOfDeal() == TypeOfDeal.SELL) {
            return sellDealchecker.isNeedClosingDeal(descriptionOfStrategy, cursor, dataOfDeal);
        } else {
            throw new RuntimeException("Некорректно определен тип TypeOfDeal=" + descriptionOfStrategy.getTypeOfDeal());
        }

    }
}
