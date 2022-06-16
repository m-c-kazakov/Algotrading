package com.finance.strategyGeneration.dataHolder;

import com.finance.strategyGeneration.strategyDescriptionParameters.*;
import com.finance.strategyGeneration.strategyDescriptionParameters.indicators.Indicator;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
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
@Builder
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataOfStrategy {


    long id;

    // это базовое значение * на количество знаков после запятой, которое есть у Pip. Находится в валютной паре
    long startScore;

    /**
     * Процент терпимого уровня потерь относительно начального капитала
     */
    long acceptableRisk;

    SumOfDealType sumOfDealType;
    Map<SumOfDealConfigurationKey, Object> sumOfDealConfigurationData;

    StopLossType stopLossType;
    Map<StopLossConfigurationKey, Object> stopLossConfigurationData;

    TrailingStopType trailingStopType;
    Map<TrailingStopConfigurationKey, Object> trailingStopConfigurationData;

    TakeProfitType takeProfitType;
    Map<TakeProfitConfigurationKey, Object> takeProfitConfigurationData;

    TypeOfDeal typeOfDeal;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Candle[] candles;
    CandlesInformation candlesInformation;

    @Getter(AccessLevel.NONE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    byte[] decisionToOpenADeal;
    DescriptionToOpenADeal descriptionToOpenADeal;

    @Getter(AccessLevel.NONE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    byte[] decisionToCloseADeal;
    DescriptionToCloseADeal descriptionToCloseADeal;

    public List<Indicator> getIndicatorsDescriptionToOpenADeal() {
        return descriptionToOpenADeal.getIndicators();
    }

    public List<Indicator> getIndicatorsDescriptionToCloseADeal() {
        return descriptionToCloseADeal.getIndicators();
    }


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
