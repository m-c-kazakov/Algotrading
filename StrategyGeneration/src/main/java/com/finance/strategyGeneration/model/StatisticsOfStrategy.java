package com.finance.strategyGeneration.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Builder
@RequiredArgsConstructor
@Table("statistics_of_strategy")
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StatisticsOfStrategy {

    @Id
    long id;
    long specificationOfStrategyId;
    long score;
    int valueOfAcceptableRisk;
    long maximumPercentDrawdownFromStartScore;
    long averagePercentDrawdownOfScore;
    long maximumValueFromScore;
    int numberOfWinningDeals;
    int numberOfLosingDeals;

}
