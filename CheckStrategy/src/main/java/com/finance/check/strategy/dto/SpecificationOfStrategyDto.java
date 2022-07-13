package com.finance.check.strategy.dto;

import com.finance.strategyDescriptionParameters.*;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Value
@Builder
@Jacksonized
public class SpecificationOfStrategyDto {

    Long id;
    Long statisticsOfStrategyId;
    String hashCode;
    Date dateOfCreation;
    Long startScore;
    Integer acceptableRisk;
    SumOfDealType sumOfDealType;
    Map<SumOfDealConfigurationKey, Object> sumOfDealConfigurationData;
    StopLossType stopLossType;
    Map<StopLossConfigurationKey, Object> stopLossConfigurationData;
    TrailingStopType trailingStopType;
    Map<TrailingStopConfigurationKey, Object> trailingStopConfigurationData;
    TakeProfitType takeProfitType;
    Map<TakeProfitConfigurationKey, Object> takeProfitConfigurationData;
    TypeOfDeal typeOfDeal;

    InformationOfCandlesDto informationOfCandles;
    List<InformationOfIndicatorDto> descriptionToOpenADeal;
    List<InformationOfIndicatorDto> descriptionToCloseADeal;
}
