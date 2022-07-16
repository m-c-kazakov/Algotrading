package com.finance.strategyGeneration.model.creator;

import com.finance.strategyGeneration.model.InformationOfCandles;

public class InformationOfCandlesCreator {

    public static InformationOfCandles createWithHashCode(InformationOfCandles informationOfCandles) {
        return informationOfCandles
                .withHashCode(String.join("_", informationOfCandles.getCurrencyPair().name(), informationOfCandles.getTimeFrame().name()));
    }
}
