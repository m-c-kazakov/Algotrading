package com.finance.check.strategy.dataHolder;

import com.finance.check.strategy.strategyDescriptionParameters.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.With;
import lombok.experimental.FieldDefaults;

import java.util.Map;
import java.util.Optional;

import static java.util.Optional.ofNullable;

/**
 * Класс хранит в себе информацию о работе стратегии
 * Данные из этого класса только на чтение
 * <p>
 * Счет представлен типом Long для удобства работы. Информация о числах после запятой находится в CurrencyPair.class
 */
@Getter
@With
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataOfStrategy {


    long id;

    // это базовое значение * на количество знаков после запятой, которое есть у Pip. Находится в валютной паре
    long startScore;

    long acceptableRisk;

    CommandTypeForDeterminingSumOfDeal commandTypeForDeterminingSumOfDeal;
    Map<SumOfDealConfigurationKey, Object> sumOfDealConfigurationData;

    StopLossType stopLossType;
    @Getter(AccessLevel.NONE)
    Map<StopLossConfigurationKey, Object> stopLossConfigurationData;

    TrailingStopType trailingStopType;
    @Getter(AccessLevel.NONE)
    Map<TrailingStopConfigurationKey, Object> trailingStopConfigurationData;

    TakeProfitType takeProfitType;
    @Getter(AccessLevel.NONE)
    Map<TakeProfitConfigurationKey, Object> takeProfitConfigurationData;

    TypeOfDeal typeOfDeal;
    @Getter(AccessLevel.NONE)
    Candle[] candles;
    CandlesInformation candlesInformation;

    @Getter(AccessLevel.NONE)
    byte[] decisionToOpenADeal;
    DescriptionToOpenADeal descriptionToOpenADeal;

    @Getter(AccessLevel.NONE)
    byte[] decisionToCloseADeal;
    DescriptionToCloseADeal descriptionToCloseADeal;


    public boolean getDecisionToOpenADeal(int cursor) {
        return decisionToOpenADeal[cursor] == 1;
    }

    public boolean getDecisionToClosingADeal(int cursor) {
        return decisionToCloseADeal.length != 0 && decisionToCloseADeal[cursor] == 1;
    }

    public int getDataLength() {
        return candles.length;
    }

    public int getClosingPrice(int cursor) {
        return candles[cursor].getClosingPrice();
    }

    public int getLowPrice(int cursor) {
        return candles[cursor].getLowPrice();
    }

    public int getHighPrice(int cursor) {
        return candles[cursor].getHighPrice();
    }

    public Optional<Object> getFromSumOfDealConfigurationData(SumOfDealConfigurationKey key) {
        return ofNullable(this.sumOfDealConfigurationData.get(key));
    }

    public Optional<Object> getFromStopLossConfigurationData(StopLossConfigurationKey key) {
        return ofNullable(this.stopLossConfigurationData.get(key));
    }

    public Optional<Object> getFromTakeProfitConfigurationData(TakeProfitConfigurationKey key) {
        return ofNullable(this.takeProfitConfigurationData.get(key));
    }

    public Optional<Object> getFromTrailingStopConfigurationData(TrailingStopConfigurationKey key) {
        return ofNullable(this.trailingStopConfigurationData.get(key));
    }
}
