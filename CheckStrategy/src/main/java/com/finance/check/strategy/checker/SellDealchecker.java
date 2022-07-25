package com.finance.check.strategy.checker;

import com.finance.dataHolder.DataOfDeal;
import com.finance.dataHolder.DescriptionOfStrategy;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component("sellDealchecker")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SellDealchecker implements MacroClosingDealchecker {

    StopLossChecker stopLossChecker;
    TrailingStopChecker trailingStopChecker;
    TakeProfitChecker takeProfitChecker;

    @Override
    public boolean isNeedClosingDeal(DescriptionOfStrategy descriptionOfStrategy, int cursor, DataOfDeal dataOfDeal) {
        // В расчет берется нижняя или высшая цена для учета колебаний
        Supplier<Boolean> stopLossCheck = executeCheckOnSell(dataOfDeal.getStopLoss(),
                descriptionOfStrategy.getClosingPrice(cursor), stopLossChecker);
        Supplier<Boolean> trailingStopCheck = executeCheckOnSell(dataOfDeal.getTrailingStop(),
                descriptionOfStrategy.getClosingPrice(cursor), trailingStopChecker);
        Supplier<Boolean> takeProfitCheck = executeCheckOnSell(dataOfDeal.getTakeProfit(),
                descriptionOfStrategy.getClosingPrice(cursor), takeProfitChecker);
        return stopLossCheck.get() || trailingStopCheck.get() || takeProfitCheck.get();
    }

    public Supplier<Boolean> executeCheckOnSell(int valueForDealChecker, int candleValue,
                                                BaseClosingDealchecker baseClosingDealchecker) {
        return () -> valueForDealChecker != 0 && baseClosingDealchecker.checkDealOnSell(valueForDealChecker,
                candleValue);
    }
}
