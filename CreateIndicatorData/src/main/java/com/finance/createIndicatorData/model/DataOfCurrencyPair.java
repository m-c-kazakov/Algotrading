package com.finance.createIndicatorData.model;

import com.finance.strategyDescriptionParameters.CandlesInformation;
import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.TimeFrame;
import com.finance.strategyDescriptionParameters.TypeOfBar;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;


@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataOfCurrencyPair {

    Long id;
    CandlesInformation candlesInformation;
    @EqualsAndHashCode.Exclude
    Map<TypeOfBar, List<Integer>> candles;

    @Builder
    public DataOfCurrencyPair(Long id, CurrencyPair currencyPair, TimeFrame timeFrame,
                              List<Integer> closingPrices, List<Integer> openingPrices,
                              List<Integer> lowPrices, List<Integer> highPrices) {
        this.id = id;
        this.candlesInformation = new CandlesInformation(currencyPair, timeFrame);
        this.candles = Map.of(
                TypeOfBar.CLOSE, closingPrices,
                TypeOfBar.OPEN, openingPrices,
                TypeOfBar.LOW, lowPrices,
                TypeOfBar.HIGH, highPrices
        );
    }

    public String candlesInformationToString() {
        return this.candlesInformation.toString();
    }

    public CurrencyPair getCurrencyPair() {
        return this.candlesInformation.getCurrencyPair();
    }

    public TimeFrame getTimeFrame() {
        return this.candlesInformation.getTimeFrame();
    }


    public List<Integer> getDataByTypeOfBar(String typeOfBar) {
        TypeOfBar key = TypeOfBar.valueOf(typeOfBar);
        return candles.get(key);
    }
}
