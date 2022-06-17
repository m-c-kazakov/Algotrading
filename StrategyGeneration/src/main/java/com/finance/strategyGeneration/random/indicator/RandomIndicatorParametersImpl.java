package com.finance.strategyGeneration.random.indicator;

import com.finance.strategyDescriptionParameters.indicators.IndicatorType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RandomIndicatorParametersImpl implements RandomIndicatorParameters {

    Map<String, RandomIndicatorParametersGenerator> randomParameters;

    @Override
    public Map<String, String> getRandomParametersByIndicatorType(IndicatorType indicatorType) {
        return randomParameters.get(indicatorType.name())
                .get();
    }
}
