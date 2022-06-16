package com.finance.strategyGeneration.strategyDescriptionParameters;

import com.finance.strategyGeneration.strategyDescriptionParameters.indicators.Indicator;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DescriptionToCloseADeal {

    List<Indicator> indicators;
}
