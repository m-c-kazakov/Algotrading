package com.finance.strategyGeneration.model;

import com.finance.strategyDescriptionParameters.indicators.IndicatorType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Builder(toBuilder = true)
@With
@RequiredArgsConstructor
@Table("information_of_indicator")
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InformationOfIndicator {

    @Id
    @EqualsAndHashCode.Exclude
    Long id;
    @EqualsAndHashCode.Exclude
    Integer hashCode;
    IndicatorType indicatorType;
    String informationOfCandlesId;
    IndicatorParametersConfigurationStorage parameters;

}
