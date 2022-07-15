package com.finance.utils.service;

import com.finance.strategyDescriptionParameters.CandlesInformation;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import com.finance.utils.dto.RequestDataOfStrategy;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DataOfCurrencyPairInitializerImpl implements DataOfCurrencyPairInitializer {
    @Override
    public Set<CandlesInformation> execute(RequestDataOfStrategy request) {

        Set<CandlesInformation> dataOfCurrencyPairs = new HashSet<>();

        dataOfCurrencyPairs.add(request.getCandlesInformation());


        addBasedOnInformationAboutDealDescription(dataOfCurrencyPairs, request.getDescriptionToOpenADeal());
        addBasedOnInformationAboutDealDescription(dataOfCurrencyPairs, request.getDescriptionToCloseADeal());

        return dataOfCurrencyPairs;
    }

    private void addBasedOnInformationAboutDealDescription(Set<CandlesInformation> dataOfCurrencyPairs,
                                                           List<Indicator> dealDescription) {
        dealDescription
                .stream().map(Indicator::getCandlesInformation)
                .forEach(dataOfCurrencyPairs::add);

    }
}
