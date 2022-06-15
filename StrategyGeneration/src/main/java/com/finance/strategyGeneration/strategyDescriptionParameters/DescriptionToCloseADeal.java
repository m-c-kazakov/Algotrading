package com.finance.strategyGeneration.strategyDescriptionParameters;

import com.finance.strategyGeneration.strategyDescriptionParameters.indicators.Indicator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DescriptionToCloseADeal {

    List<Indicator> indicators;
}
