package com.finance.strategyDescriptionParameters;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public enum TakeProfitType {
    FIXED_TAKE_PROFIT;

    public static List<TakeProfitType> getTakeProfitTypes() {
        return List.of(TakeProfitType.values());
    }

    public static TakeProfitType getRandomTakeProfitType() {
        List<TakeProfitType> takeProfitTypes = getTakeProfitTypes();
        return takeProfitTypes.get(ThreadLocalRandom.current()
                .nextInt(takeProfitTypes.size()));
    }
}
