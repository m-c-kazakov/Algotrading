package com.finance.check.strategy.checker;

import org.springframework.stereotype.Component;

@Component
public class TakeProfitChecker implements BaseClosingDealchecker {
    @Override
    public boolean checkDealOnBuy(int takeProfit, int dataOfCandle) {
//        dataOfDeal.getTakeProfit() <= dataOfStrategy.getClosingPrice(cursor)
        // TODO передавать сюда для покупки минимальную цену в свече для продажи максимальную цену в свече
        // TODO ОБЯЗАТЕЛЬНО ПРЕДУСМОТРЕТЬ ЧТОБЫ ДАННЫЕ В INT хранились корректно
        // т.е. число 0.9 и 0.85 были как 90 и 85, а не 9 и 85
        return takeProfit <= dataOfCandle;
    }

    @Override
    public boolean checkDealOnSell(int takeProfit, int dataOfCandle) {
        return takeProfit >= dataOfCandle;
    }
}
