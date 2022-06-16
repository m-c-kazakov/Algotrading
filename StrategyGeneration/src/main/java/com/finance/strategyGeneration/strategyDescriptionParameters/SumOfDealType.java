package com.finance.strategyGeneration.strategyDescriptionParameters;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum SumOfDealType {

    PERCENT_OF_SCORE;

    public static List<SumOfDealType> getSumOfDealTypes() {
        return List.of(SumOfDealType.values());
    }

    public static SumOfDealType getRandomSumOfDealType() {
        List<SumOfDealType> sumOfDealTypes = getSumOfDealTypes();
        return sumOfDealTypes.get(ThreadLocalRandom.current()
                .nextInt(sumOfDealTypes.size()));
    }
}
