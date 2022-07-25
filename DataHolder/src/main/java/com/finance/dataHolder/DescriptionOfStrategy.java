package com.finance.dataHolder;

import com.finance.strategyDescriptionParameters.*;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
@Slf4j
@Getter
@With
@Builder
@EqualsAndHashCode
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DescriptionOfStrategy {

    @EqualsAndHashCode.Exclude
    Long id;

    // это базовое значение * на количество знаков после запятой, которое есть у Pip. Находится в валютной паре
    Long startScore;
    Long acceptableRisk;
    @NotBlank
    SumOfDealType sumOfDealType;
    @NotEmpty
    Map<SumOfDealConfigurationKey, String> sumOfDealConfigurationData;

    @NotBlank
    StopLossType stopLossType;
    @NotEmpty
    Map<StopLossConfigurationKey, String> stopLossConfigurationData;
    @NotBlank
    TrailingStopType trailingStopType;
    @NotEmpty
    Map<TrailingStopConfigurationKey, String> trailingStopConfigurationData;
    @NotBlank
    TakeProfitType takeProfitType;
    @NotEmpty
    Map<TakeProfitConfigurationKey, String> takeProfitConfigurationData;
    @NotBlank
    TypeOfDeal typeOfDeal;
    @Getter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    List<DataOfCandle> dataOfCandles;
    @NonNull
    CandlesInformation candlesInformation;

    @Getter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    List<Byte> decisionToOpenADeal;
    @NotEmpty
    List<Indicator> descriptionToOpenADeal;

    @Getter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    List<Byte> decisionToCloseADeal;
    @NotEmpty
    List<Indicator> descriptionToCloseADeal;


    public boolean getDecisionToOpenADeal(int cursor) {
        try {
            return decisionToOpenADeal.get(cursor) == 1;
        } catch (Exception exception) {
            log.error("decisionToOpenADeal.size={}; dataOfCandles.size={};", decisionToOpenADeal.size(), dataOfCandles.size());
            throw exception;
        }
    }

    public boolean getDecisionToClosingADeal(int cursor) {
        return !decisionToCloseADeal.isEmpty() && decisionToCloseADeal.get(cursor) == 1;
    }

    public int getDataLength() {
        return dataOfCandles.size();
    }

    public int getClosingPrice(int cursor) {
        return dataOfCandles.get(cursor).getClosingPrices();
    }

    public int getLowPrice(int cursor) {
        return dataOfCandles.get(cursor).getLowPrices();
    }

    public int getHighPrice(int cursor) {
        return dataOfCandles.get(cursor).getHighPrices();
    }

    public Optional<String> getFromSumOfDealConfigurationData(SumOfDealConfigurationKey key) {
        return ofNullable(this.sumOfDealConfigurationData.get(key));
    }

    public Optional<String> getFromStopLossConfigurationData(StopLossConfigurationKey key) {
        return ofNullable(this.stopLossConfigurationData.get(key));
    }

    public Optional<String> getFromTakeProfitConfigurationData(TakeProfitConfigurationKey key) {
        return ofNullable(this.takeProfitConfigurationData.get(key));
    }

    public Optional<String> getFromTrailingStopConfigurationData(TrailingStopConfigurationKey key) {
        return ofNullable(this.trailingStopConfigurationData.get(key));
    }

    public List<Indicator> getIndicatorsDescriptionToOpenADeal() {
        return descriptionToOpenADeal.stream().map(Indicator::copy).toList();
    }

    public List<Indicator> getIndicatorsDescriptionToCloseADeal() {
        return descriptionToCloseADeal.stream().map(Indicator::copy).toList();
    }
}
