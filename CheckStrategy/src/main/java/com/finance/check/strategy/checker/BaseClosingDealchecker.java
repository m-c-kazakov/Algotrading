package com.finance.check.strategy.checker;

public interface BaseClosingDealchecker {

    boolean checkDealOnBuy(int ruleValue, int dataOfCandle);

    boolean checkDealOnSell(int ruleValue, int dataOfCandle);
}
