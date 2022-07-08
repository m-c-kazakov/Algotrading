package com.finance.strategyGeneration.model;

import com.finance.strategyDescriptionParameters.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

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
    //    @ValueConverter()
    ConfigurationStorage<SumOfDealConfigurationKey> sumOfDealConfigurationData;

    StopLossType stopLossType;
    // https://stackoverflow.com/questions/68984665/persist-java-map-as-postgresql-jsonb-using-spring-data-jdbc
    ConfigurationStorage<StopLossConfigurationKey> stopLossConfigurationData;

    TrailingStopType trailingStopType;
    ConfigurationStorage<TrailingStopConfigurationKey> trailingStopConfigurationData;

    TakeProfitType takeProfitType;
    ConfigurationStorage<TakeProfitConfigurationKey> takeProfitConfigurationData;

    TypeOfDeal typeOfDeal;

    // TODO В базе хранятся ID а тут объекты
    Long informationOfCandlesId;
    List<Long> descriptionToOpenADeal;
    List<Long> descriptionToCloseADeal;
}
