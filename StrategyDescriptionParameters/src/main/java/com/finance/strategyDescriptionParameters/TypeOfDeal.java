package com.finance.strategyDescriptionParameters;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public enum TypeOfDeal {

    BUY,
    SELL;

    public static List<TypeOfDeal> getTypeOfDeals() {
        return List.of(TypeOfDeal.values());
    }

    public static TypeOfDeal getRandomTypeOfDeal() {
        List<TypeOfDeal> typeOfDeals = getTypeOfDeals();
        return typeOfDeals.get(ThreadLocalRandom.current().nextInt(typeOfDeals.size()));
    }

}
