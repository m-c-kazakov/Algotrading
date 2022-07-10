package com.finance.strategyGeneration.model;

import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.TimeFrame;
import com.finance.strategyGeneration.model.creator.InformationOfCandlesStorageCreator;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode
public class InformationOfCandlesStorage {

    InformationOfCandles informationOfCandles;

    public Long getId() {
        return informationOfCandles.getId();
    }

    public String getStringId() {
        return String.valueOf(getId());
    }

    public TimeFrame getTimeFrame() {
        return informationOfCandles.getTimeFrame();
    }

    public CurrencyPair getCurrencyPair() {
        return informationOfCandles.getCurrencyPair();
    }

    public InformationOfCandlesStorage withTimeFrame(TimeFrame timeFrame) {
        return InformationOfCandlesStorageCreator.create(informationOfCandles.withTimeFrame(timeFrame));
    }
}
