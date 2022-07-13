package com.finance.strategyGeneration.model.storage;

import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.TimeFrame;
import com.finance.strategyGeneration.model.InformationOfCandles;
import com.finance.strategyGeneration.model.creator.InformationOfCandlesStorageCreator;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@ToString
@EqualsAndHashCode
public class InformationOfCandlesStorage {

    InformationOfCandles informationOfCandles;

    public Long receiveId() {
        return informationOfCandles.getId();
    }

    public String receiveStringId() {
        return String.valueOf(receiveId());
    }

    public TimeFrame receiveTimeFrame() {
        return informationOfCandles.getTimeFrame();
    }

    public CurrencyPair receiveCurrencyPair() {
        return informationOfCandles.getCurrencyPair();
    }

    public InformationOfCandlesStorage withTimeFrame(TimeFrame timeFrame) {
        return InformationOfCandlesStorageCreator.create(informationOfCandles.withTimeFrame(timeFrame));
    }

    public String receiveHashCode() {
        return this.informationOfCandles.getHashCode();
    }
}
