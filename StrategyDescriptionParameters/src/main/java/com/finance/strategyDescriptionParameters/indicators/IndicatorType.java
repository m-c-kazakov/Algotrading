package com.finance.strategyDescriptionParameters.indicators;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum IndicatorType {

    SMA("Simple moving average", SmaParameters.getParameters());


    String name;
    List<String> namesOfIndicatorParameters;

    public static List<IndicatorType> getIndicatorTypes() {
        return List.of(IndicatorType.values());
    }

    public static IndicatorType getRandomIndicatorType() {
        List<IndicatorType> indicatorTypes = getIndicatorTypes();
        return indicatorTypes.get(ThreadLocalRandom.current()
                .nextInt(indicatorTypes.size()));
    }

}
