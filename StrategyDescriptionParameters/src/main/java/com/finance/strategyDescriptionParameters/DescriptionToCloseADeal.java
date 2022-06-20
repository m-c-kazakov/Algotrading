package com.finance.strategyDescriptionParameters;

import com.finance.strategyDescriptionParameters.indicators.Indicator;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DescriptionToCloseADeal implements DealDescription {

    List<Indicator> indicators;
    public List<Indicator> getIndicators() {
        return new ArrayList<>(indicators);
    }
}
