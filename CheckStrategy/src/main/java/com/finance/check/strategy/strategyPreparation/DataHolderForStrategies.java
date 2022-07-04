package com.finance.check.strategy.strategyPreparation;

import com.finance.dataHolder.DataOfCandle;
import com.finance.strategyDescriptionParameters.CandlesInformation;
import com.finance.strategyDescriptionParameters.DescriptionToCloseADeal;
import com.finance.strategyDescriptionParameters.DescriptionToOpenADeal;

public interface DataHolderForStrategies {

    DataOfCandle[] getDataOfCandles(CandlesInformation candlesInformation);

    byte[] getDecisionToOpenADeal(DescriptionToOpenADeal descriptionToOpenADeal);

    byte[] getDecisionToCloseADeal(DescriptionToCloseADeal descriptionToOpenADeal);
}
