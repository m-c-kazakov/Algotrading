package com.finance.strategyGeneration.crossing;

import com.finance.strategyDescriptionParameters.indicators.Indicator;

import java.util.List;

public interface TypesOfCrosses {
    List<?> singlePointCrossing(List<Indicator> first, List<Indicator> second, int separator);
}
