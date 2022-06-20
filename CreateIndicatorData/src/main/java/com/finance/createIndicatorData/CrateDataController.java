package com.finance.createIndicatorData;


import com.finance.dataHolder.Candle;
import com.finance.strategyDescriptionParameters.CandlesInformation;
import com.finance.strategyDescriptionParameters.DescriptionToCloseADeal;
import com.finance.strategyDescriptionParameters.DescriptionToOpenADeal;

public interface CrateDataController {

    Candle[] getDataOfCandles(CandlesInformation candlesInformation);

    byte[] getDecisionToOpenADeal(DescriptionToOpenADeal descriptionToOpenADeal);

    byte[] getDecisionToCloseADeal(DescriptionToCloseADeal descriptionToOpenADeal);
}
