package com.finance.createIndicatorData.model;

import com.finance.dataHolder.Candle;
import com.finance.strategyDescriptionParameters.CandlesInformation;
import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.TimeFrame;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;


@Getter
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataOfCurrencyPair {

    Long id;
    CandlesInformation candlesInformation;
    @EqualsAndHashCode.Exclude
    Candle candle;

    public String candlesInformationToString() {
        return this.candlesInformation.toString();
    }

    @Builder
    public DataOfCurrencyPair(Long id, CurrencyPair currencyPair, TimeFrame timeFrame, Candle candle){
        this.id = id;
        this.candlesInformation = new CandlesInformation(currencyPair, timeFrame);
        this.candle = candle;
    }

    public CurrencyPair getCurrencyPair() {
        return this.candlesInformation.getCurrencyPair();
    }

    public TimeFrame getTimeFrame() {
        return this.candlesInformation.getTimeFrame();
    }
}
