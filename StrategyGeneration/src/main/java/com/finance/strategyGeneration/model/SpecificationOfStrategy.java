package com.finance.strategyGeneration.model;

import com.finance.strategyDescriptionParameters.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.Map;

@Value
@Builder
@EqualsAndHashCode
public class SpecificationOfStrategy {

    long id;

    long strategyInformationId;

    long hashCode;

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

    CandlesInformation candlesInformation;
    DescriptionToOpenADeal descriptionToOpenADeal;
    DescriptionToCloseADeal descriptionToCloseADeal;
}
