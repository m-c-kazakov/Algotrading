package com.finance.strategyDescriptionParameters;

import com.finance.strategyDescriptionParameters.indicators.Indicator;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.ArrayList;
import java.util.List;

@Value
@Jacksonized
@Builder
public class DescriptionToCloseADeal implements DealDescription {

    List<Indicator> indicators;
    public List<Indicator> getIndicators() {
        return new ArrayList<>(indicators);
    }
}
