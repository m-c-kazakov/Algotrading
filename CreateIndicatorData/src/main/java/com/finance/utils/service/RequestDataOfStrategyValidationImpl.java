package com.finance.utils.service;

import com.finance.utils.dto.RequestDataOfStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
public class RequestDataOfStrategyValidationImpl implements RequestDataOfStrategyValidation {

    @Override
    public void execute(RequestDataOfStrategy request) {

        timeFrameValidation(request);

    }

    private void timeFrameValidation(RequestDataOfStrategy request) {
        Stream.of(request.getDescriptionToOpenADeal(), request.getDescriptionToCloseADeal())
                .flatMap(List::stream)
                .filter(indicator -> request.getCandlesInformation().getTimeFrame().getPer() > indicator.getTimeFrame().getPer())
                .findFirst()
                .ifPresent(indicator -> {
                    throw new RuntimeException(
                            "TimeFrame биржевых данных больше чем TimeFrame одного из индикаторов CandlesInformation.TimeFrame=%s; Indicator.TimeFrame=%s;".formatted(
                                    request.getCandlesInformation().getTimeFrame().name(),
                                    indicator.getTimeFrame().name()));
                });
    }
}
