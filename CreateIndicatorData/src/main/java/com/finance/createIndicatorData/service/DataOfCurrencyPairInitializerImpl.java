package com.finance.createIndicatorData.service;

import com.finance.createIndicatorData.dto.DataOfCurrencyPair;
import com.finance.createIndicatorData.dto.RequestDataOfStrategy;
import com.finance.strategyDescriptionParameters.CandlesInformation;
import com.finance.strategyDescriptionParameters.DealDescription;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataOfCurrencyPairInitializerImpl implements DataOfCurrencyPairInitializer {
    @Override
    public Set<DataOfCurrencyPair> execute(RequestDataOfStrategy request) {

        Set<DataOfCurrencyPair> dataOfCurrencyPairs = new HashSet<>();

        addBasedOnInformationAboutCandles(dataOfCurrencyPairs, request.getCandlesInformation());

        addBasedOnInformationAboutDealDescription(dataOfCurrencyPairs, request.getDescriptionToOpenADeal());
        addBasedOnInformationAboutDealDescription(dataOfCurrencyPairs, request.getDescriptionToCloseADeal());

        return dataOfCurrencyPairs;
    }

    private void addBasedOnInformationAboutDealDescription(Set<DataOfCurrencyPair> dataOfCurrencyPairs,
                                                           DealDescription dealDescription) {
        dealDescription
                .getIndicators()
                .stream()
                .map(indicator -> DataOfCurrencyPair.builder()
                        .currencyPair(indicator.getCurrencyPair())
                        .timeFrame(indicator.getTimeFrame())
                        .build())
                .forEach(dataOfCurrencyPairs::add);

    }

    private void addBasedOnInformationAboutCandles(Set<DataOfCurrencyPair> dataOfCurrencyPairs,
                                                                 CandlesInformation candlesInformation) {
        DataOfCurrencyPair dataOfCurrencyPair = DataOfCurrencyPair.builder()
                .currencyPair(candlesInformation.getCurrencyPair())
                .timeFrame(candlesInformation.getTimeFrame())
                .build();
        dataOfCurrencyPairs.add(dataOfCurrencyPair);
    }
}
