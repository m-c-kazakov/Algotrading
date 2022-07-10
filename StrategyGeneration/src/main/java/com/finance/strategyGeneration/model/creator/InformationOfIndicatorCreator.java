package com.finance.strategyGeneration.model.creator;

import com.finance.strategyDescriptionParameters.indicators.IndicatorType;
import com.finance.strategyGeneration.model.IndicatorParametersConfigurationStorage;
import com.finance.strategyGeneration.model.InformationOfCandles;
import com.finance.strategyGeneration.model.InformationOfIndicator;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Map;

@Component
public class InformationOfIndicatorCreator {

    public InformationOfIndicator create(@NonNull IndicatorType indicatorType,
                                         @NonNull InformationOfCandles informationOfCandles,
                                         @NonNull Map<String, String> parameters) {
        Assert.notEmpty(parameters, "parameters у индикатора не может быть пустым");
        return InformationOfIndicator.builder()
                .indicatorType(indicatorType)
                .informationOfCandlesId(String.valueOf(informationOfCandles.getId()))
                .parameters(new IndicatorParametersConfigurationStorage(parameters))
                .build();
    }
}
