package com.finance.strategyGeneration.strategyDescriptionParameters;

import com.finance.strategyGeneration.strategyDescriptionParameters.indicators.Indicator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DescriptionToOpenADeal {

    List<Indicator> indicators;

    public List<Indicator> getIndicators() {
        return new ArrayList<>(indicators);
    }
}
