package com.finance.check.strategy.checker;

import org.springframework.stereotype.Component;

@Component
public class TrailingStopChecker implements BaseClosingDealchecker {
    @Override
    public boolean checkDealOnBuy(int trailingStop, int dataOfCandle) {
        return trailingStop >= dataOfCandle;
    }

    @Override
    public boolean checkDealOnSell(int trailingStop, int dataOfCandle) {
        return trailingStop <= dataOfCandle;
    }
}
