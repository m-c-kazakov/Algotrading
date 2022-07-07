package com.finance.strategyGeneration.model;

import com.finance.strategyDescriptionParameters.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;
import java.util.Map;

@Getter
@Builder
@RequiredArgsConstructor
@Table("specification_of_strategy")
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SpecificationOfStrategy {

    @Id
    long id;

    long statisticsOfStrategyId;

    long hashCode;

    long startScore;

    long acceptableRisk;

    SumOfDealType sumOfDealType;
    Map<SumOfDealConfigurationKey, Object> sumOfDealConfigurationData;

    StopLossType stopLossType;
    Map<StopLossConfigurationKey, Object> stopLossConfigurationData;

    TrailingStopType trailingStopType;
    Map<TrailingStopConfigurationKey, Object> trailingStopConfigurationData;

    TakeProfitType takeProfitType;
    Map<TakeProfitConfigurationKey, Object> takeProfitConfigurationData;

    TypeOfDeal typeOfDeal;

    // TODO В базе хранятся ID а тут объекты
    Long candlesInformationId;
    List<Long> descriptionToOpenADeal;
    List<Long> descriptionToCloseADeal;
}
