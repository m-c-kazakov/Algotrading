package com.finance.createIndicatorData;

import com.finance.strategyDescriptionParameters.*;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
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
