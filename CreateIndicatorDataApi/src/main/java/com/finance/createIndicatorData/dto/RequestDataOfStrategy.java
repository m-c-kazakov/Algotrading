package com.finance.createIndicatorData.dto;

import com.finance.strategyDescriptionParameters.CandlesInformation;
import com.finance.strategyDescriptionParameters.TimeFrame;
import com.finance.strategyDescriptionParameters.TypeOfDeal;
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
    List<Indicator> descriptionToOpenADeal;
    List<Indicator> descriptionToCloseADeal;

    public TimeFrame getTheSmallestTimeFrame() {
        return candlesInformation.getTimeFrame();
    }

    public List<Indicator> getIndicatorsOfDescriptionToOpenADeal() {
        return descriptionToOpenADeal;
    }

    public List<Indicator> getIndicatorsOfDescriptionToCloseADeal() {
        return descriptionToCloseADeal;
    }
}
