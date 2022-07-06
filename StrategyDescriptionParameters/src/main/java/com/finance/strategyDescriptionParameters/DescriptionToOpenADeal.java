package com.finance.strategyDescriptionParameters;

import com.finance.strategyDescriptionParameters.indicators.Indicator;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.ArrayList;
import java.util.List;

@Value
@Jacksonized
@Builder
@EqualsAndHashCode
public class DescriptionToOpenADeal implements DealDescription {

    List<Indicator> indicators;

    public List<Indicator> getIndicators() {
        return new ArrayList<>(indicators);
    }
}
