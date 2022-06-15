package com.finance.check.strategy.strategyPreparation;

import com.finance.check.strategy.dataHolder.Candle;
import com.finance.check.strategy.strategyDescriptionParameters.CandlesInformation;
import com.finance.check.strategy.strategyDescriptionParameters.DescriptionToCloseADeal;
import com.finance.check.strategy.strategyDescriptionParameters.DescriptionToOpenADeal;

public interface DataHolderForStrategies {

    Candle[] getDataOfCandles(CandlesInformation candlesInformation);

    byte[] getDecisionToOpenADeal(DescriptionToOpenADeal descriptionToOpenADeal);

    byte[] getDecisionToCloseADeal(DescriptionToCloseADeal descriptionToOpenADeal);
}
