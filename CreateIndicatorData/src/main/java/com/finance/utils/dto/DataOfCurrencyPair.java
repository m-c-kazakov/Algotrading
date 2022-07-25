package com.finance.utils.dto;

import com.finance.strategyDescriptionParameters.CandlesInformation;
import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.TimeFrame;
import com.finance.strategyDescriptionParameters.TypeOfBar;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Getter
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
        Assert.notEmpty(closingPrices, "Коллекция closingPrices не может быть пустой");
        Assert.notEmpty(openingPrices, "Коллекция openingPrices не может быть пустой");
        Assert.notEmpty(lowPrices, "Коллекция lowPrices не может быть пустой");
        Assert.notEmpty(highPrices, "Коллекция highPrices не может быть пустой");
        Assert.state(Stream.of(
                closingPrices.size(),
                openingPrices.size(),
                lowPrices.size(),
                highPrices.size()
        ).collect(Collectors.toSet()).size() == 1, "Коллекции с биржевыми данными должны иметь одинаковый размер");

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

    public List<Integer> getDataByTypeOfBar(String typeOfBar) {
        TypeOfBar key = TypeOfBar.valueOf(typeOfBar);
        return candles.get(key);
    }

    public int getSize() {
        return candles.get(TypeOfBar.CLOSE).size();
    }

    public void subList(int fromIndex) {
        candles.get(TypeOfBar.CLOSE).subList(fromIndex, candles.get(TypeOfBar.CLOSE).size());
        candles.get(TypeOfBar.OPEN).subList(fromIndex, candles.get(TypeOfBar.OPEN).size());
        candles.get(TypeOfBar.HIGH).subList(fromIndex, candles.get(TypeOfBar.HIGH).size());
        candles.get(TypeOfBar.LOW).subList(fromIndex, candles.get(TypeOfBar.LOW).size());
    }
}
