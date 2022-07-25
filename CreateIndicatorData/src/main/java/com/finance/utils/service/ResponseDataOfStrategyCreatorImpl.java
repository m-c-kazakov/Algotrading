package com.finance.utils.service;

import com.finance.dataHolder.DataOfCandle;
import com.finance.strategyDescriptionParameters.CandlesInformation;
import com.finance.utils.dto.ResponseDataOfStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResponseDataOfStrategyCreatorImpl implements ResponseDataOfStrategyCreator {
    @Override
    public ResponseDataOfStrategy execute(List<Byte> decisionOnOpeningDeal,
                                          CandlesInformation candlesInformation,
                                          List<DataOfCandle> candles) {
        if (decisionOnOpeningDeal.size() < candles.size()) {
            int difference = candles.size() - decisionOnOpeningDeal.size();
            candles = candles.subList(difference, candles.size());
        } else if (decisionOnOpeningDeal.size() > candles.size()) {
            int difference = decisionOnOpeningDeal.size() - candles.size();
            decisionOnOpeningDeal = decisionOnOpeningDeal.subList(difference, decisionOnOpeningDeal.size());
        }


        return ResponseDataOfStrategy
                .builder()
                .candles(candles)
                .candlesInformation(candlesInformation)
                .decisionToOpenADeal(decisionOnOpeningDeal)
                .decisionToCloseADeal(List.of())
                .build();
    }
}
