package com.finance.strategyDescriptionParameters;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public enum StopLossType {

    FIXED_STOP_LOSS;

    public static List<StopLossType> getStopLossTypes() {
        return List.of(StopLossType.values());
    }

    public static StopLossType getRandomStopLossType() {
        List<StopLossType> stopLossTypes = getStopLossTypes();
        return stopLossTypes.get(ThreadLocalRandom.current()
                .nextInt(stopLossTypes.size()));
    }


}
