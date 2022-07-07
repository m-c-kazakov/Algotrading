package com.finance.strategyGeneration.model;

import com.finance.strategyDescriptionParameters.CandlesInformation;
import com.finance.strategyDescriptionParameters.indicators.IndicatorType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Map;

@Getter
@Builder
@RequiredArgsConstructor
@Table("information_of_indicator")
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InformationOfIndicator {
    @Id
    long id;
    IndicatorType indicatorType;
    CandlesInformation candlesInformation;
    Map<String, String> parameters;
}
