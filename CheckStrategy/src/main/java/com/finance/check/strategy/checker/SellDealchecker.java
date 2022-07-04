package com.finance.check.strategy.checker;

import com.finance.dataHolder.DataOfDeal;
import com.finance.dataHolder.DescriptionOfStrategy;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;
import java.util.stream.Stream;

@Component("sellDealchecker")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SellDealchecker implements MacroClosingDealchecker {

    StopLossChecker stopLossChecker;
    TrailingStopChecker trailingStopChecker;
    TakeProfitChecker takeProfitChecker;

    @Override
    public boolean isNeedClosingDeal(DescriptionOfStrategy descriptionOfStrategy, int cursor, DataOfDeal dataOfDeal) {
        Supplier<Boolean> stopLossCheck = executeCheckOnSell(dataOfDeal.getStopLoss(),
                descriptionOfStrategy.getHighPrice(cursor), stopLossChecker);
        Supplier<Boolean> trailingStopCheck = executeCheckOnSell(dataOfDeal.getTrailingStop(),
                descriptionOfStrategy.getHighPrice(cursor), trailingStopChecker);
        Supplier<Boolean> takeProfitCheck = executeCheckOnSell(dataOfDeal.getTakeProfit(),
                descriptionOfStrategy.getLowPrice(cursor), takeProfitChecker);
        return Stream.of(stopLossCheck, trailingStopCheck, takeProfitCheck)
                .anyMatch(Supplier::get);
    }

    public Supplier<Boolean> executeCheckOnSell(int valueForDealChecker, int candleValue,
                                                BaseClosingDealchecker baseClosingDealchecker) {
        return () -> valueForDealChecker != 0 && baseClosingDealchecker.checkDealOnSell(valueForDealChecker,
                candleValue);
    }
}
