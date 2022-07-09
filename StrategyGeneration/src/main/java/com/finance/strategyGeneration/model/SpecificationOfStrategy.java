package com.finance.strategyGeneration.model;

import com.finance.strategyDescriptionParameters.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@With
@Getter
@Builder
@ToString
@RequiredArgsConstructor
@Table("specification_of_strategy")
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SpecificationOfStrategy {

    @Id
    @EqualsAndHashCode.Exclude
    Long id;
    @EqualsAndHashCode.Exclude
    Long statisticsOfStrategyId;
    @EqualsAndHashCode.Exclude
    Integer hashCode;
    @NotNull
    Long startScore;
    @NotNull
    Integer acceptableRisk;
    @NotBlank
    SumOfDealType sumOfDealType;
    @NotEmpty
    ConfigurationStorage<SumOfDealConfigurationKey> sumOfDealConfigurationData;
    @NotBlank
    StopLossType stopLossType;
    @NotEmpty
    ConfigurationStorage<StopLossConfigurationKey> stopLossConfigurationData;
    @NotBlank
    TrailingStopType trailingStopType;
    @NotEmpty
    ConfigurationStorage<TrailingStopConfigurationKey> trailingStopConfigurationData;
    @NotBlank
    TakeProfitType takeProfitType;
    @NotEmpty
    ConfigurationStorage<TakeProfitConfigurationKey> takeProfitConfigurationData;
    @NotBlank
    TypeOfDeal typeOfDeal;

    @NotBlank
    String informationOfCandlesId;
    @NotEmpty
    List<String> descriptionToOpenADeal;
    @NotEmpty
    List<String> descriptionToCloseADeal;
}
