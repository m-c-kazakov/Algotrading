package com.finance.check.strategy.checker;

import org.springframework.stereotype.Component;

@Component
public class TakeProfitChecker implements BaseClosingDealchecker {
    @Override
    public boolean checkDealOnBuy(int takeProfit, int dataOfCandle) {
        return takeProfit <= dataOfCandle;
    }

    @Override
    public boolean checkDealOnSell(int takeProfit, int dataOfCandle) {
        return takeProfit >= dataOfCandle;
    }
}
