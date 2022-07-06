package com.finance.strategyGeneration.model;

import com.finance.dataHolder.DataOfCandle;
import com.finance.strategyDescriptionParameters.*;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document
@Builder
@Value
public class SpecificationOfStrategy {

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

    List<DataOfCandle> dataOfCandles;
    CandlesInformation candlesInformation;

    List<Byte> decisionToOpenADeal;
    DescriptionToOpenADeal descriptionToOpenADeal;

    List<Byte> decisionToCloseADeal;
    DescriptionToCloseADeal descriptionToCloseADeal;
}
