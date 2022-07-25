package com.finance.check.strategy.checker;

import com.finance.dataHolder.DataOfDeal;
import com.finance.dataHolder.DescriptionOfStrategy;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component("buyDealchecker")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BuyDealchecker implements MacroClosingDealchecker {

    StopLossChecker stopLossChecker;
    TrailingStopChecker trailingStopChecker;
    TakeProfitChecker takeProfitChecker;

    @Override
    public boolean isNeedClosingDeal(DescriptionOfStrategy descriptionOfStrategy, int cursor, DataOfDeal dataOfDeal) {
        // В расчет берется нижняя или высшая цена для учета колебаний
        Supplier<Boolean> stopLossCheck = executeCheckOnBuy(dataOfDeal.getStopLoss(),
                descriptionOfStrategy.getLowPrice(cursor), stopLossChecker);
        Supplier<Boolean> trailingStopCheck = executeCheckOnBuy(dataOfDeal.getTrailingStop(),
                descriptionOfStrategy.getLowPrice(cursor), trailingStopChecker);
        Supplier<Boolean> takeProfitCheck = executeCheckOnBuy(dataOfDeal.getTakeProfit(),
                descriptionOfStrategy.getHighPrice(cursor), takeProfitChecker);

        return stopLossCheck.get() || trailingStopCheck.get() || takeProfitCheck.get();
    }

    public Supplier<Boolean> executeCheckOnBuy(int dataOfDealValue, int candleValue,
                                               BaseClosingDealchecker baseClosingDealchecker) {
        return () -> dataOfDealValue != 0 && baseClosingDealchecker.checkDealOnBuy(dataOfDealValue,
                candleValue);
    }
}
