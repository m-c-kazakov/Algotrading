package com.finance.check.strategy.checker;

import org.springframework.stereotype.Component;

@Component
public class StopLossChecker implements BaseClosingDealchecker {

    @Override
    public boolean checkDealOnBuy(int stopLoss, int dataOfCandle) {
        return stopLoss >= dataOfCandle;
    }

    @Override
    public boolean checkDealOnSell(int stopLoss, int dataOfCandle) {
        return stopLoss <= dataOfCandle;
    }
}
