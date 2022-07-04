package com.finance.createIndicatorData.dto;

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

    CandlesInformation candlesInformation;
    @EqualsAndHashCode.Exclude
    Map<TypeOfBar, List<Integer>> candles;

    @Builder
    public DataOfCurrencyPair(CurrencyPair currencyPair, TimeFrame timeFrame,
                              List<Integer> closingPrices, List<Integer> openingPrices,
                              List<Integer> lowPrices, List<Integer> highPrices) {
        this.candlesInformation = CandlesInformation.builder().currencyPair(currencyPair).timeFrame(timeFrame).build();
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

    public int getPer() {
        return this.getTimeFrame().getPer();
    }


    public List<Integer> getDataByTypeOfBar(String typeOfBar) {
        TypeOfBar key = TypeOfBar.valueOf(typeOfBar);
        return candles.get(key);
    }
}
