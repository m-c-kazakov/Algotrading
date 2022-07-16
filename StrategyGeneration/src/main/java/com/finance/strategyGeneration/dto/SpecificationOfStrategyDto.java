package com.finance.strategyGeneration.dto;

import com.finance.strategyDescriptionParameters.*;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.Map;

@Value
@Builder
@Jacksonized
public class SpecificationOfStrategyDto {

    Long id;
    Long startScore;
    Integer acceptableRisk;
    SumOfDealType sumOfDealType;
    Map<SumOfDealConfigurationKey, String> sumOfDealConfigurationData;
    StopLossType stopLossType;
    Map<StopLossConfigurationKey, String> stopLossConfigurationData;
    TrailingStopType trailingStopType;
    Map<TrailingStopConfigurationKey, String> trailingStopConfigurationData;
    TakeProfitType takeProfitType;
    Map<TakeProfitConfigurationKey, String> takeProfitConfigurationData;
    TypeOfDeal typeOfDeal;

    InformationOfCandlesDto informationOfCandles;
    List<InformationOfIndicatorDto> descriptionToOpenADeal;
    List<InformationOfIndicatorDto> descriptionToCloseADeal;
}
