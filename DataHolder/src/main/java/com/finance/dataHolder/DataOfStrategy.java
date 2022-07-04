package com.finance.dataHolder;

import com.finance.strategyDescriptionParameters.*;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
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
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataOfStrategy {


    long id;

    // это базовое значение * на количество знаков после запятой, которое есть у Pip. Находится в валютной паре
    long startScore;

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
    @Getter(AccessLevel.NONE)
    DataOfCandle[] dataOfCandles;
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
        return dataOfCandles.length;
    }

    public int getClosingPrice(int cursor) {
        return dataOfCandles[cursor].getClosingPrices();
    }

    public int getLowPrice(int cursor) {
        return dataOfCandles[cursor].getLowPrices();
    }

    public int getHighPrice(int cursor) {
        return dataOfCandles[cursor].getHighPrices();
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

    public List<Indicator> getIndicatorsDescriptionToOpenADeal() {
        return descriptionToOpenADeal.getIndicators().stream().map(Indicator::copy).toList();
    }

    public List<Indicator> getIndicatorsDescriptionToCloseADeal() {
        return descriptionToCloseADeal.getIndicators();
    }
}
