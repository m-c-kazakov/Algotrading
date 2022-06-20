package com.finance.createIndicatorData;

import com.finance.strategyDescriptionParameters.CandlesInformation;
import com.finance.strategyDescriptionParameters.DescriptionToCloseADeal;
import com.finance.strategyDescriptionParameters.DescriptionToOpenADeal;
import com.finance.strategyDescriptionParameters.TimeFrame;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RequestDataOfStrategy {

    CandlesInformation candlesInformation;
    DescriptionToOpenADeal descriptionToOpenADeal;
    DescriptionToCloseADeal descriptionToCloseADeal;

    public TimeFrame getTheSmallestTimeFrame() {
        return candlesInformation.getTimeFrame();
    }
}
