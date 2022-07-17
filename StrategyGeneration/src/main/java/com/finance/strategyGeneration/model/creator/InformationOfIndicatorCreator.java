package com.finance.strategyGeneration.model.creator;

import com.finance.strategyDescriptionParameters.indicators.IndicatorType;
import com.finance.strategyGeneration.model.InformationOfCandles;
import com.finance.strategyGeneration.model.InformationOfIndicator;
import com.finance.strategyGeneration.model.storage.IndicatorParametersConfigurationStorage;
import com.finance.strategyGeneration.model.storage.InformationOfCandlesStorage;
import lombok.NonNull;
import org.springframework.util.Assert;

import java.util.Map;

public class InformationOfIndicatorCreator {

    public static InformationOfIndicator create(@NonNull IndicatorType indicatorType,
                                                @NonNull InformationOfCandles informationOfCandles,
                                                @NonNull Map<String, String> parameters) {
        Assert.notEmpty(parameters, "parameters у индикатора не может быть пустым");
        return InformationOfIndicator
                .builder()
                .indicatorType(indicatorType)
                .informationOfCandles(new InformationOfCandlesStorage(informationOfCandles))
                .parameters(new IndicatorParametersConfigurationStorage(parameters))
                .build();
    }
}
