package com.finance.strategyGeneration.model.creator;

import com.finance.strategyGeneration.model.InformationOfCandles;
import com.finance.strategyGeneration.model.InformationOfCandlesStorage;
import lombok.NonNull;

public class InformationOfCandlesStorageCreator {


    public static InformationOfCandlesStorage create(@NonNull String id) {
        InformationOfCandles informationOfCandles = InformationOfCandles.builder().id(Long.valueOf(id)).build();
        return InformationOfCandlesStorageCreator.create(informationOfCandles);
    }

    public static InformationOfCandlesStorage create(@NonNull InformationOfCandles informationOfCandles) {
        return new InformationOfCandlesStorage(informationOfCandles.withHashCode(informationOfCandles.hashCode()));
    }
}
