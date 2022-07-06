package com.finance.strategyDescriptionParameters;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public enum TrailingStopType {
    FIXED_TRAILING_STOP_TYPE;

    public static List<TrailingStopType> getTrailingStopTypes() {
        return List.of(TrailingStopType.values());
    }

    public static TrailingStopType getRandomTrailingStopType() {
        List<TrailingStopType> trailingStopTypes = getTrailingStopTypes();
        return trailingStopTypes.get(ThreadLocalRandom.current()
                .nextInt(trailingStopTypes.size()));
    }
}
