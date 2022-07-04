package com.finance.check.strategy.checker;

import com.finance.dataHolder.DataOfDeal;
import com.finance.dataHolder.DescriptionOfStrategy;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;
import java.util.stream.Stream;

@Component("buyDealchecker")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BuyDealchecker implements MacroClosingDealchecker {

    StopLossChecker stopLossChecker;
    TrailingStopChecker trailingStopChecker;
    TakeProfitChecker takeProfitChecker;

    @Override
    public boolean isNeedClosingDeal(DescriptionOfStrategy descriptionOfStrategy, int cursor, DataOfDeal dataOfDeal) {
        Supplier<Boolean> stopLossCheck = executeCheckOnBuy(dataOfDeal.getStopLoss(),
                descriptionOfStrategy.getLowPrice(cursor), stopLossChecker);
        Supplier<Boolean> trailingStopCheck = executeCheckOnBuy(dataOfDeal.getTrailingStop(),
                descriptionOfStrategy.getLowPrice(cursor), trailingStopChecker);
        Supplier<Boolean> takeProfitCheck = executeCheckOnBuy(dataOfDeal.getTakeProfit(),
                descriptionOfStrategy.getHighPrice(cursor), takeProfitChecker);

        return Stream.of(stopLossCheck, trailingStopCheck, takeProfitCheck)
                .anyMatch(Supplier::get);
    }

    public Supplier<Boolean> executeCheckOnBuy(int valueForDealChecker, int candleValue,
                                               BaseClosingDealchecker baseClosingDealchecker) {
        return () -> valueForDealChecker != 0 && baseClosingDealchecker.checkDealOnBuy(valueForDealChecker,
                candleValue);
    }
}
