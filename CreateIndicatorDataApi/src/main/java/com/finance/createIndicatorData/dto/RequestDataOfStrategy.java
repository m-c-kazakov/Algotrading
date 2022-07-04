package com.finance.createIndicatorData.dto;

import com.finance.strategyDescriptionParameters.*;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;


@Value
@Jacksonized
@Builder
public class RequestDataOfStrategy {

    TypeOfDeal typeOfDeal;
    CandlesInformation candlesInformation;
    DescriptionToOpenADeal descriptionToOpenADeal;
    DescriptionToCloseADeal descriptionToCloseADeal;

    public TimeFrame getTheSmallestTimeFrame() {
        return candlesInformation.getTimeFrame();
    }

    public List<Indicator> getIndicatorsOfDescriptionToOpenADeal() {
        return descriptionToOpenADeal.getIndicators();
    }

    public List<Indicator> getIndicatorsOfDescriptionToCloseADeal() {
        return descriptionToCloseADeal.getIndicators();
    }
}
